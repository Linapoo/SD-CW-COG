<template>
	<ul>
		<li v-for="(item,idx) in data" :key="idx" @click.stop="$emit('item-click',item)" @mouseenter="$emit('hover',{item,idx})">
			<img :src="`http://www.cog.codes/api/game/getImage?gameId=${item.id}`" alt />
			<a>{{item.name}}</a>
		</li>
	</ul>
</template>

<script>
export default {
	props: {
		data: {
			type: Array,
			default: () => ({})
		}
	}
}
</script>

<style lang="less" scoped>
ul {
	display: flex;
	width: 900px;
	flex-wrap: wrap;
	justify-content: space-between;
	img {
		width: 150px;
		height: 150px;
	}
	li {
		margin-right: 40px;
		display: flex;
		flex-direction: column;
        margin-bottom: 20px;
        width: 150px;
	}
}
</style>