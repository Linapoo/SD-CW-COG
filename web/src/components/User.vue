<template>
	<at-tooltip placement="top" :content="content">
		<at-button size="small" type="text" @click="sendReq">{{userInfo.userName}}</at-button>
	</at-tooltip>
</template>

<script>
export default {
	computed: {
		isYourself() {
			return this.userInfo.id === sessionStorage.getItem('userId')
		},
		content() {
			return this.isYourself ? 'Yourself' : 'Send a friend request'
		}
	},
	props: {
		userInfo: {
			type: Object
		}
	},
	methods: {
		sendReq() {
			if (this.isYourself) { return }
			this.$http({
				url: '/friendship/sendFriendRequest',
				method: 'post',
				data: {
					senderId: sessionStorage.getItem('userId'),
					receiverId: this.userInfo.id
				}
			}).then(() => {
				this.$Notify.success({
					title: 'Success',
				})
			})
		}
	}
}
</script>

<style>
</style>