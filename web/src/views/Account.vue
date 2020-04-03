<template>
	<div class="wrapper">
		<div class="form">
			<div class="form-item">
				<div class="label">Avatar</div>
				<img :src="userAvatar" v-if="userAvatar" style="width:100px;height:100px;" />
				<input type="file" style="opacity: 0" id="fielinput" @change="readFile " />
				<at-button @click="handleUploadAvatar">Upload</at-button>
			</div>
			<div class="form-item">
				<div class="label">userName</div>
				<at-input v-model="userInfo.userName"></at-input>
			</div>
			<div class="form-item">
				<div class="label">email</div>
				<at-input v-model="userInfo.email"></at-input>
			</div>
			<div class="form-item">
				<div class="label">city</div>
				<at-input v-model="userInfo.city"></at-input>
			</div>
			<div class="form-item">
				<div class="label">gender</div>
				<at-radio v-model="userInfo.gender" :label="0">Male</at-radio>
				<at-radio v-model="userInfo.gender" :label="1">Female</at-radio>
			</div>
			<div class="form-item">
				<div class="label">age</div>
				<at-input-number :min="0" :max="100" v-model="userInfo.age"></at-input-number>
			</div>
			<br />
			<at-button type="primary" @click="submit">Submit</at-button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<at-button type="primary" @click="showModal = true">Reset Password</at-button>
			<at-modal
				v-model="showModal"
				title="Reset Password"
				@on-confirm="handleConfirm"
				@on-cancel="handleCancel"
			>
				<div class="form">
					<div class="form-item">
						<div class="label">Old Password</div>
						<at-input type="password" v-model="resetInfo.oldPwd"></at-input>
					</div>
					<div class="form-item">
						<div class="label">New Password</div>
						<at-input type="password" v-model="resetInfo.newPwd"></at-input>
					</div>
				</div>
			</at-modal>
		</div>
	</div>
</template>

<script>
export default {
	created() {
		// this.userInfo = JSON.parse(sessionStorage.getItem('userInfo')) || {}
		this.getMyInfo()
		this.getMyAvatar()

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

			// Determine whether it is a picture type
			if (!/image\/\w+/.test(file.type)) {
				alert("You can only choose pictures");
				return false;
            }
            if(file.size/1000 > 500) {
                alert('The Image is too large')
                return
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
					callback: (action) => {
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