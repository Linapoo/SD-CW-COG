<template>
	<div class="wrapper-trend">
		<h1>Trend</h1>
		<ul>
			<li v-for="(item,idx) in data" :key="idx" style="display: flex;justify-content: space-between;align-items: center;">
				<span>
					{{idx + 1}} &nbsp;&nbsp;&nbsp;
					<a @click="go(item.id)">{{item.name}}</a>
				</span>
				<at-rate :value="item.score" disabled></at-rate>
			</li>
		</ul>
	</div>
</template>

<script>
export default {
	props: {
		data: {
			type: Array,
			default: () => ({})
		}
	},
	methods: {
		go(id) {
			console.log(id)
			this.$emit('go', id)
		}
	}
}
</script>

<style lang="less" scoped>
.wrapper-trend {
    min-width: 350px;
    overflow: auto;
}
ul {
	li {
		border-bottom: 1px solid #6190e8;
		text-align: left;
		padding-left: 10px;
		height: 35px;
        line-height: 35px;
        span {
            width: 200px;
        }
	}
}
</style>