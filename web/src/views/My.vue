<template>
	<div class="wrapper">
		<div class="form">
			<h2>My Games</h2>

			<at-table :columns="gameCols" :data="gameList" :border="false"></at-table>
			<br />
			<br />
			<h2>My Friends</h2>

			<at-table :columns="friendCols" :data="friendList" :border="false"></at-table>

			<br />
			<br />
			<h2>My Club</h2>

			<at-table :columns="clubCols" :data="clubList" :border="false"></at-table>
		</div>
	</div>
</template>

<script>
export default {
	created() {
		// this.userInfo = JSON.parse(sessionStorage.getItem('userInfo')) || {}
		// this.getMyInfo()
		// this.getMyAvatar()
		this.getMyGames()
		this.getMyFriends()

	},
	data() {
		return {
			userInfo: {},
			resetInfo: {},
			showModal: false,
			clubList: [],
			gameCols: [
				{ title: 'Name', key: 'gameName' },
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
										url: '/game/delToUser',
										method: 'Delete',
										data: {
											gameId: params.item.id,
											userId: sessionStorage.getItem('userId')
										}
									}).then(res => {
										this.$Notify({
											title: 'Success',
											type: 'success'
										})
										this.getMyGames()
									})
								}
							}
						}, 'Delete')
					}
				}
			],
			clubCols: [
				{ title: 'userName', key: 'userName' },
			],
			friendCols: [
				{ title: 'userName', key: 'userName' },
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
										url: '/friendship/delete',
										method: 'delete',
										data: {
											friendId: params.item.id,
											userId: sessionStorage.getItem('userId')
										}
									}).then(res => {
										this.$Notify({
											title: 'Success',
											type: 'success'
										})
										this.getMyFriends()
									})
								}
							}
						}, 'Delete')
					}
				}
			],
			gameList: [],
			friendList: [],
			userAvatar: '',
		}
	},
	methods: {
		getMyInfo() {
			this.$http({
				url: '/user/getUserInfo',
				method: 'get',
				data: {
					uid: sessionStorage.getItem('userId'),
					targetId: sessionStorage.getItem('userId'),
				}
			}).then(res => {
				this.userInfo = res.user
			})
		},
		getMyAvatar() {
			this.userAvatar = `http://www.cog.codes/api/user/getAvatar?uid=${sessionStorage.getItem('userId')}`
			// this.$http({
			// 	url: '/user/getAvatar',
			// 	method: 'get',
			// 	data: {
			// 		uid: sessionStorage.getItem('userId'),
			// 	}
			// }).then(res => {
			// 	this.userAvatar = res.user
			// })
		},
		readFile(e) {
			var file = e.target.files[0];

			//Determine whether it is a picture type
			if (!/image\/\w+/.test(file.type)) {
				alert("Only pictures can be selected.");
				return false;
			}
			let formData = new FormData();
			formData.append("avatar", file);
			formData.append("uid", sessionStorage.getItem('userId'))
			fetch('http://www.cog.codes/api/user/updateAvatar', {
				method: 'POST',
				body: formData
			}).then(() => {

				this.$Notify({
					title: 'Success',
					type: 'success'
				})
				window.location.reload()
			})
		},
		handleConfirm() {
			this.$http({
				url: '/user/resetPwd',
				method: 'put',
				data: {
					...this.resetInfo,
					uid: sessionStorage.getItem('userId')
				}
			}).then(res => {
				this.$Modal.alert({
					title: 'Success',
					content: 'Please login again',
					callback: function (action) {
						this.$router.push('/')
					}
				})

			})
		},
		handleCancel() {

		},
		getMyGames() {

			this.$http({
				url: '/game/viewUserGame',
				method: 'get',
				data: {
					userId: sessionStorage.getItem('userId'),
					pageSize: 9999,
					pageNo: 1
				}
			}).then(res => {
				this.gameList = res.data || []
			})
		},
		submit() {
			this.$http({
				url: '/user/updateUserInfo',
				method: 'put',
				data: {
					...this.userInfo,
					uid: sessionStorage.getItem('userId')
				}
			}).then(res => {
				this.$Notify({
					title: 'Success',
					type: 'success'
				})
				sessionStorage.setItem('userName', this.userInfo.userName)
				sessionStorage.setItem('userInfo', JSON.stringify(this.userInfo))
			})
		},
		handleUploadAvatar() {
			document.getElementById('fielinput').click()
		},
		getMyFriends() {
			this.$http({
				url: '/friendship/myFriends',
				method: 'get',
				data: {
					userId: sessionStorage.getItem('userId'),
					pageSize: 9999,
					pageNo: 1
				}
			}).then(res => {
				this.friendList = res.data || []
			})
		}
	}

}
</script>

<style lang="less" scoped>
.wrapper {
	display: flex;
	justify-content: center;
	padding-top: 50px;
}
.form {
	width: 500px;

	.label {
		font-size: 16px;
		font-weight: bold;
		margin-top: 20px;
	}
}
</style>