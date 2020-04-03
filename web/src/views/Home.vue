<template>
	<div>
		<at-alert
			message="New Friends"
			:description="`You have ${tableData.length} friend requests`"
			type="info"
			show-icon
			closable
			v-if="tableData.length"
			@click.native="showFriends = true"
		></at-alert>
		<div class="nav">
			<h2>Types</h2>
			<br />
			<at-radio-group v-model="currType">
				<at-radio-button v-for="type in types" :key="type" :label="type">{{type}}</at-radio-button>
			</at-radio-group>
		</div>
		<div class="wrapper">
			<List :data="list" style="margin-right: 50px;" @item-click="handleClick"></List>
			<Trend :data="trend" @go="go"></Trend>
		</div>
		<at-modal title="New Friends" v-model="showFriends">
			<at-table :columns="cols" :data="tableData" :border="false"></at-table>
		</at-modal>
	</div>
</template>

<script>
import List from '@/components/List'
import Trend from '@/components/Trend'

export default {
	components: { List, Trend },
	data() {
		return {
			currType: '',
			list: new Array(1).fill({ img: 'https://misc.aotu.io/koppthe/at-ui/cover.jpg', name: 'TOM' }),
			trend: new Array(1).fill({ name: 'TOM' }),
			pageSize: 999,
			pageNo: 1,
			totalPage: 1,
			showFriends: false,
			tableData: [],
			types: ['All', 'Abstract', 'Customizable', 'Thematic', 'Family', 'Children', 'Party', 'Strategy', 'War',],
			cols: [
				{ title: 'userName', key: 'userName' },
				{ title: 'requestTime', key: 'requestTime' },
				{
					title: 'Action',
					render: (h, params) => {
						return h('AtButton', {
							props: {
								size: 'smaller',
								type: 'info',
								hollow: true
							},
							on: {
								click: () => {
									this.$http({
										url: '/friendship/acceptFriendship',
										method: 'post',
										data: {
											friendId: params.item.id,
											userId: sessionStorage.getItem('userId')

										}
									}).then(res => {
										this.$Notify({
											title: 'Success',
											type: 'success'
										})
										this.getFrendRequest()
									})
								}
							}
						}, 'Accept')
					}
				}
			]
		}
	},
	created() {
		this.getAllGames()
		// this.getGamesByType()
		this.getFrendRequest()
		this.getGameTrend()
	},
	watch: {
		currType(val) {
			if (val === 'All') {
				this.getAllGames()
			} else {
				this.getGamesByType()
			}
		}
	},
	methods: {
		handleClick(item) {
			this.$router.push('/page/game?id=' + item.id)
		},
		getGamesByType() {
			this.$http({
				url: '/game/searchType',  //viewAll
				method: 'get',
				data: {
					pageSize: this.pageSize,
					pageNo: this.pageNo,
					type: this.currType
				}
			}).then(res => {
				this.list = res.data.map(v => ({
					name: v.gameName,
					id: v.id
				}))
				this.totalPage = res.totalPage
			})
		},
		getFrendRequest() {
			this.$http({
				url: '/friendship/getFriendRequests',
				method: 'get',
				data: {
					pageSize: this.pageSize,
					pageNo: this.pageNo,
					userId: sessionStorage.getItem('userId')
				}
			}).then(res => {
				if (res.data) {
					this.tableData = res.data
				}
			})
		},
		getAllGames() {
			this.$http({
				url: '/game/viewAll',  //viewAll
				method: 'get',
				data: {
					pageSize: this.pageSize,
					pageNo: this.pageNo
				}
			}).then(res => {
				this.list = res.data.map(v => ({
					name: v.gameName,
					id: v.id
				}))
				this.totalPage = res.totalPage
			})
		},
		go(id) {
			this.$router.push('/page/game?id=' + id)
		},
		getGameTrend() {
			this.$http({
				url: '/game/sortScore',
				method: 'get',
				data: {
					pageSize: 10,
					pageNo: 1
				}
			}).then(res => {
				if (res.data) {
					this.trend = res.data.map(v => ({
						name: v.gameName,
						score: v.averageScore,
						id: v._id
					}))
				}
			})
		},
		handleHover({item,idx}) {
			if (!item.img) {
				this.$http({
					url: '/game/getImage',
					method: 'get',
					data: {
						gameId: item.id,
					}
				}).then(res => {


				})
			}
		}
	}
}
</script>

<style lang="less" scoped>
.wrapper {
	margin-top: 50px;
	display: flex;
	// justify-content: space-between;
	padding: 0 200px;
}
.nav {
	padding: 20px 200px;
	a {
		// margin-right: 30px;
	}
}
</style>