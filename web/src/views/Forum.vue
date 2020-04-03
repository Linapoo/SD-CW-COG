<template>
	<div class="wrapper">
		<div class="card">
			<h1>{{game.gameName}}</h1>
			<!-- <h3>Game Description:</h3> -->
			<br />
			<h4>{{game.description}}</h4>
		</div>
		<div class="tab">
			<div></div>
			<at-button type="warning" size="small" @click="showDialog = true">+ speak</at-button>
		</div>
		<at-table :columns="cols" :data="tableData" :border="false"></at-table>
		<at-modal
			v-model="showDialog"
			title="Create a post"
			@on-confirm="handleConfirm"
			@on-cancel="handleCancel"
		>
			<div class="form">
				<div class="form-item">
					<div class="label">title</div>
					<at-input v-model="newPost.title"></at-input>
				</div>
				<div class="form-item">
					<div class="label">content</div>
					<at-textarea v-model="newPost.content" :min-rows="4"></at-textarea>
				</div>
			</div>
		</at-modal>
		<at-modal v-model="showPost" title="Replies" width="1200">
			<div class="form" style="width: 95%">
				<div class="form-item">
					<div class="label">title</div>
					<at-input v-model="currPost.title" readonly></at-input>
				</div>
				<div class="form-item">
					<div class="label">content</div>
					<at-textarea v-model="currPost.content" :min-rows="4" readonly></at-textarea>
				</div>

				<div class="form-item">
					<div class="label">replies</div>
					<at-table :columns="replyCol" :data="replyList" :border="false"></at-table>
				</div>
				<br />
				<at-button type="primary" v-if="showAddReplyBtn" @click="showAddReply = true">Add a Reply</at-button>
				<br />
				<br />
				<div class="form-item" v-if="showAddReply">
					<at-textarea v-model="replyContent" :min-rows="4"></at-textarea>
					<br />
					<at-button @click="addReply('')">Submit</at-button>
				</div>
			</div>
		</at-modal>
	</div>
</template>

<script>
export default {
	data() {
		return {
			id: this.$route.query.id,
			game: { name: 'xxx', desc: 'xxxx' },
			cols: [
				{
					title: 'Discuss',
					key: 'discuss',
					render: (h, params) => {
						return h('AtButton', {
							props: {
								size: 'small',
								type: 'text'
							},
							on: {
								click: () => {
									this.currPost = params.item
									this.getReply(params.item.id)
									this.showPost = true
								}
							}
						}, params.item.discuss)
					}
				},
				{
					title: 'Author',
					render: (h, params) => {
						return h('User', {
							props: {
								userInfo: params.item.author,
							},
						})
					}
				},
				{ title: 'Respond', key: 'numReply' },
				{ title: 'FinalResponse', key: 'finalResponse' },
				{
					title: 'Detail',
					render: (h, params) => {
						return h('AtButton', {
							props: {
								size: 'smaller',
								type: 'info',
								hollow: true
							},
							on: {
								click: () => {
									this.currPost = params.item
									this.getReply(params.item.id)
									this.showPost = true
								}
							}
						}, 'Replies')
					}
				},
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
								display: params.item.author.id === sessionStorage.getItem('userId') ? 'block' : 'none'
							},
							on: {
								click: () => {
									this.$http({
										url: '/forum/deletePost',
										method: 'delete',
										data: {
											postId: params.item.id,
											userId: sessionStorage.getItem('userId')
										}
									}).then(res => {
										this.getPosts()
									})
								}
							}
						}, 'Delete')
					}
				},
			],
			showPost: false,
			tableData: [],
			showDialog: false,
			newPost: {},
			forumInfo: {},
			currPost: {},
			replyContent: '',
			showAddReply: false,
			replyCol: [
				{
					title: 'Author',
					render: (h, params) => {
						return h('User', {
							props: {
								userInfo: params.item.author,
							},
						})
					}
				},
				{ title: 'Content', key: 'content' },
				{ title: 'Time', key: 'replyTime' },
				{ title: 'TargetReply', key: 'targetReplyContent' },
				{
					title: 'Action',
					key: 'discuss',
					render: (h, params) => {
						return h('AtButton', {
							props: {
								size: 'small',
							},
							on: {
								click: () => {
									this.showAddReplyBtn = false
									this.showAddReply = true
									this.currTargetReply = params.item.id
								}
							}
						}, 'Reply')
					}
				},
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
								display: params.item.author.id === sessionStorage.getItem('userId') ? 'block' : 'none'
							},
							on: {
								click: () => {
									this.$http({
										url: '/forum/deleteReply',
										method: 'delete',
										data: {
											replyId: params.item.id,
											userId: sessionStorage.getItem('userId')
										}
									}).then(res => {
										this.getReply(this.currPost.id)
									})
								}
							}
						}, 'Delete')
					}
				},
			],
			replyList: [],
			currTargetReply: '',
			showAddReplyBtn: true,
		}
	},
	created() {
		this.getGameInfo()
		this.getForumInfo().then(() => {
			this.getPosts()
		})
	},
	methods: {
		getGameInfo() {
			this.$http({
				url: '/game/view',
				method: 'get',
				data: {
					gameId: this.id
				}
			}).then(res => {
				this.game = res
			})
		},
		getReply(id) {
			this.$http({
				url: '/forum/getReplies',
				method: 'get',
				data: {
					postId: id,
					pageNo: 1,
					pageSize: 99999
				}
			}).then(res => {
				this.replyList = res.data.map(v => {
					return {
						targetReplyContent: v.targetReply && v.targetReply.content,
						...v,
						replyTime: new Date(v.replyTime).toLocaleString()
					}
				})
			})
		},
		getForumInfo() {
			return this.$http({
				url: '/forum/getForumInfo',
				method: 'get',
				data: {
					gameId: this.id
				}
			}).then(res => {
				this.forumInfo = res
			})
		},
		getPosts() {
			this.$http({
				url: '/forum/getPosts',
				method: 'get',
				data: {
					forumId: this.forumInfo.id,
					pageNo: 1,
					pageSize: 99999
				}
			}).then(res => {
				this.tableData = res.data.map(v => {
					let content = v.content
					return {
						...v,
						discuss: v.title,
						finalResponse: new Date(v.finalReplyTime).toLocaleString(),
						content,
						numReply: v.numReply,
						id: v.id,
					}
				})
			})
		},
		handleConfirm() {
			this.$http({
				url: '/forum/createPost',
				method: 'post',
				data: {
					...this.newPost,
					forumId: this.forumInfo.id,
					authorId: sessionStorage.getItem('userId')
				}
			}).then(res => {
				this.getForumInfo()
				this.getPosts()
			})
		},
		handleCancel() {

		},
		addReply() {
			const params = {
				postId: this.currPost.id,
				content: this.replyContent,
				authorId: sessionStorage.getItem('userId')
			}
			if (this.currTargetReply) {
				params.targetReplyId = this.currTargetReply
			}
			this.$http({
				url: '/forum/addReply',
				method: 'post',
				data: params
			}).then(res => {
				this.showAddReply = false
				this.currTargetReply = ''
				this.showAddReplyBtn = true
				this.getReply(this.currPost.id)
				this.replyContent = ''
			})
		}
	}
}
</script>

<style lang="less" scoped>
.wrapper {
	width: 70vw;
	margin-top: 50px;
	margin-left: 100px;
	text-align: left;

	.card {
		height: 250px;
		background: #fff4e9;
		padding: 20px;
		h1 {
			font-size: 36px;
		}
	}
	.tab {
		display: flex;
		justify-content: space-between;
		padding: 20px;
	}
	ul {
		margin-top: 30px;
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