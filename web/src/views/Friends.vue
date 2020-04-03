<template>
	<div class="wrapper">
		<List :data="list" style="margin-right: 100px;"></List>
		<Trend :data="trend"></Trend>
	</div>
</template>

<script>
import List from '@/components/List'
import Trend from '@/components/Trend'

export default {
    components: {List,Trend},
	data() {
		return {
			list: new Array(8).fill({img: 'https://misc.aotu.io/koppthe/at-ui/cover.jpg',name: 'TOM'}),
			trend: new Array(8).fill({name: 'TOM'})
		}
	}
}
</script>

<style lang="less" scoped>
.wrapper {
    margin-top: 150px;
    display: flex;
    // justify-content: space-between;
    padding: 0 200px;
}
</style>