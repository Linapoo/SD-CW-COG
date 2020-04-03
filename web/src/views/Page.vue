<template>
	<div>
		<header>
			<div class="top">
				<div>
					<router-link to="home">Main Page</router-link>
					<!-- <router-link to="home">Categories</router-link> -->
					<!-- <router-link to="game">All Games</router-link> -->
				</div>
				<div>
					<router-link to="my">My</router-link>
					<router-link to="account">Account</router-link>
					<router-link to="/">Logout</router-link>
				</div>
			</div>
			<div class="bottom">
				<img src="../assets/logo.png" alt />
				<at-input v-model="query" size="large" style="width: 50vw" append-button>
					<template slot="append">
						<i class="icon icon-search" @click="searchGame"></i>
					</template>
				</at-input>
			</div>
		</header>
		<router-view :key="$route.fullPath" />
	</div>
</template>

<script>
export default {
	data() {
		return {
			query: ''
		}
	},
	methods: {
		searchGame() {
			if (!this.query) {
				return
			}
			this.$http({
				url: '/game/search',
				method: 'get',
				data: {
					gameName: this.query,
					pageSize: 9999999,
					pageNo: 1
				}
			}).then(res => {
				if (res.data && res.data.length) {
					const game = res.data[0]
					this.query = ''
					this.$router.push('/page/game?id=' + game.id)
				} else {
					alert('No Results')
				}
			})
		}
	}
}

</script>

<style lang="less" scoped>
header {
	height: 150px;
	display: flex;
	flex-direction: column;
	background: rgb(241, 244, 246);
	.top {
		height: 40px;
		background: rgba(0, 0, 0, 0.5);
		line-height: 40px;
		padding: 0 20px;
		a {
			color: white;
			margin: 0 10px;
		}
		display: flex;
		justify-content: space-between;
	}
	.bottom {
		display: flex;
		justify-content: center;
		align-items: center;
		flex: 1;
		img {
			height: 100px;
			margin-right: 50px;
		}
	}
}
</style>