<template>
	<div>
		<div class="info">
			<div class="title">
				<h2>{{game.gameName}}</h2>
				<at-button type="primary" icon="icon-plus" @click="collect" v-if="!isCollected">collect</at-button>
				<at-button type="warning" v-else icon="icon-trash-2" @click="del">delete</at-button>
				<at-button
					type="text"
					style="margin-left: 100px"
					@click="goForum"
				>Go to Forums of {{game.gameName}} >></at-button>
			</div>
			<div class="base">
				<img :src="`http://www.cog.codes/api/game/getImage?gameId=${id}`" alt />
				<div style="min-width: 400px;">
					<div>
						<span>Type</span>
						{{game.type}}
					</div>
					<div>
						<span>Artist</span>
						{{game.artist}}
					</div>
					<div>
						<span>Designer</span>
						{{game.designer}}
					</div>

					<div>
						<span>TimePerRound</span>
						{{game.timePerRound}}
					</div>
					<div>
						<span>Year</span>
						{{game.year}}
					</div>
					<div>
						<span>PlayerAge</span>
						{{game.playerAge}}
					</div>
				</div>
				<div class="buy" style="margin-left: 100px;min-width: 300px;">
					<h3>Purchase</h3>
					<br />
					<a :href="game.link" target="_blank">Official Website</a>
					&nbsp;&nbsp;
					Price: £{{game.price}}
					<br />

					<br />
					<at-button hollow type="info" @click="buy">Amazon.com</at-button>
				</div>
			</div>

			<br />
			<br />
			<div>
				<h2>Description</h2>
				{{game.description}}
			</div>
			<div class="comments">
				<h2>Short Comments...</h2>
				<at-button type="primary" icon="icon-edit" @click="showAdd = true">Write a Short Review</at-button>
				<br />
				<br />
				<at-table :columns="cols" :data="commentList" :border="false"></at-table>
			</div>
		</div>
		<at-modal @on-confirm="handleConfirm" title="Short Review" v-model="showAdd">
			<div class="form">
				<div class="form-item">
					<div class="label">Content</div>
					<at-textarea v-model="comment.content"></at-textarea>
				</div>
				<br />
				<br />
				<div class="form-item">
					<div class="label">Score</div>
					<at-rate v-model="comment.score"></at-rate>
				</div>
			</div>
		</at-modal>
	</div>
</template>

<script>
export default {
	data() {
		return {
			game: {},
			id: '',
			userGames: [],
			showAdd: false,
			comment: {},
			commentList: [],
			cols: [
				{
					title: 'Reviewer',
					key: 'reviewerName',
					render: (h, params) => {
						return h('User', {
							props: {
								userInfo: params.item.reviewer,
							},
						})
					}
				},
				{ title: 'Content', key: 'content' },
				{
					title: 'Score',
					key: 'score',
					render: (h, params) => {
						return h('AtRate', {
							props: {
								disabled: true,
								value: params.item.score
							},
						}, params.item.discuss)
					}
				},
				{ title: 'Time', key: 'postTime' },
				{
					title: 'Action',
					render: (h, params) => {
						return h('AtButton', {
							props: {
								size: 'smaller',
								type: 'info',
								hollow: true
							},
							style: {
								display: params.item.reviewer.id === sessionStorage.getItem('userId') ? 'block' : 'none'
							},
							on: {
								click: () => {
									this.$http({
										url: '/review/delete',
										method: 'delete',
										data: {
											reviewId: params.item.id
										}
									}).then(res => {
										this.getComments()
									})
								}
							}
						}, 'Delete')
					}
				},
			]
		}
	},
	created() {
		this.id = this.$route.query.id
		this.getGameInfo().then(() => {
			this.getComments()
			// this.getGameImg()
		})
		this.getUserGame()

	},
	computed: {
		isCollected() {
			return this.userGames.includes(this.id)
		}
	},
	methods: {
		getGameImg() {
			this.$http({
				url: '/game/getImage?gameId=5e81cf28ced64930812d9b1a',
				method: 'get',
			}).then(res => {
				this.game.img = res

			})
		},
		goForum() {
			this.$router.push('/page/forum?id=' + this.id)
		},
		getGameInfo() {
			return this.$http({
				url: '/game/view',
				method: 'get',
				data: {
					gameId: this.id
				}
			}).then(res => {
				this.game = res
			})
		},

		getUserGame() {
			this.$http({
				url: '/game/viewUserGame',
				method: 'get',
				data: {
					userId: sessionStorage.getItem('userId'),
					pageSize: 9999999,
					pageNo: 1
				}
			}).then(res => {
				if (res.data) {
					this.userGames = res.data.map(v => v.id)
				}

			})
		},
		del() {
			this.$http({
				url: '/game/delToUser',
				method: 'delete',
				data: {
					gameId: this.id,
					userId: sessionStorage.getItem('userId')
				}
			}).then(res => {
				this.getUserGame()
				this.$Notify({
					title: 'Success',
					type: 'success'
				})
			})
		},
		collect() {
			this.$http({
				url: '/game/addToUser',
				method: 'post',
				data: {
					gameId: this.id,
					userId: sessionStorage.getItem('userId')
				}
			}).then(res => {
				this.getUserGame()
				this.$Notify({
					title: 'Success',
					type: 'success'
				})
			})
		},
		handleConfirm() {
			this.$http({
				url: '/review/post',
				method: 'post',
				data: {
					reviewerId: sessionStorage.getItem('userId'),
					gameId: this.id,
					anonymous: 0,
					...this.comment
				}
			}).then(res => {
				this.getComments()
				this.showAdd = false
				this.comment = {}
			})
		},
		buy() {
			window.open('https://www.amazon.co.uk/s?k=' + encodeURI(this.game.gameName))
		},
		getComments() {
			this.$http({
				url: '/review/viewGameReview',
				method: 'get',
				data: {
					gameId: this.id,
					pageSize: 9999999,
					pageNo: 1
				}
			}).then(res => {
				this.commentList = res.data.map(v => {
					return {
						...v,
						reviewerName: v.reviewer.userName,
						postTime: new Date(v.postTime).toLocaleString(),
						targetUserId: v.reviewer.id
					}
				})
			})
		}
	}
}
</script>

<style lang="less" scoped>
.info {
	margin-left: 200px;
	img {
		height: 200px;
		min-width: 200px;
	}
	h2 {
		margin: 20px 0;
	}
	.title {
		display: flex;
		align-items: center;
		h2 {
			margin-right: 20px;
		}
	}
	.base {
		display: flex;
		// justify-content: space-between;
		width: 1000px;
		img {
			margin-right: 30px;
		}
		> div {
			> div {
				display: flex;
				span {
					font-weight: bold;
					font-size: 14px;
					width: 120px;
					display: block;
					color: #555;
				}
				margin-bottom: 8px;
				font-weight: bold;
				font-size: 16px;
				color: #333;
			}
		}
	}
	.comments {
		margin-top: 50px;
		// width: 100%;
		width: 1200px;
	}
	.form {
		width: 500px;

		.label {
			font-size: 16px;
			font-weight: bold;
			margin-top: 20px;
		}
	}
}

</style>