<template>
	<div class="container">
		<div class="content">
			<div class="login-container animated fadeInDown" style="animation-delay:.3s;">
				<!--   Login   -->
				<div class="login justify-content-center" id="login-form">
					<h1 class="form-title">
						<i class="fas fa-user" style="color:#55a0ff;"></i>
						<br />Sign Up
						<hr />
					</h1>

					<div class="form-container animated fadeIn" style="animation-delay:.7s;">
						<form style="height: 60vh;overflow: auto;">
							<label for>
								<i class="fab fa-amilia"></i> Name
							</label>
							<input type="text" name="name" placeholder="Name" v-model="info.userName" required autofocus />

							<label for>
								<i class="fas fa-at"></i> Email
							</label>
							<input type="email" name="email" v-model="info.email" placeholder="E-mail" />

							<label for>
								<i class="fab fa-slack-hash"></i> Password
							</label>
							<input type="password" name="password" v-model="info.password" placeholder="Password" />

							<label for>
								<i class="fab fa-slack-hash"></i> Confirm Password
							</label>
							<input type="password" name="password_confirmation" placeholder="Password" />
							<label for>
								<i class="fab fa-amilia"></i> Age
							</label>
							<input type="text" placeholder="Age" v-model="info.age" required autofocus />
							<label for>
								<i class="fab fa-amilia"></i> Gender
							</label>
							<br />
							<br />
							<div style="display: flex;">
								<span style="width: 50px;">Male</span>
								<input type="radio" style="width: 150px;" name="gender" :value="0" v-model="info.gender" />
							</div>
							<div style="display: flex;">
								<span style="width: 50px;">Female</span>
								<input type="radio" style="width: 150px;" name="gender" :value="1" v-model="info.gender" />
							</div>

							<label for>
								<i class="fab fa-amilia"></i> City
							</label>
							<input type="text" placeholder="City" v-model="info.city" required autofocus />
							<div class="submit-buttons">
								<input value="Submit" @click="register" style="background:#55efc4;" />
							</div>
						</form>
					</div>
				</div>
				<!--   Login   -->

				<div
					class="login animated fadeIn"
					style="animation-delay:.7s;animation-duration:4s;"
					id="login-bg"
				></div>
			</div>
		</div>
	</div>
</template>

<script>


export default {
	name: 'home',
	components: {

	},
	data() {
		return {
			info: {}
		}
	},
	created() {

	},
	methods: {
		register() {
			this.$http({
				url: '/user/register',
				method: 'post',
				data: this.info
			}).then(res => {
				this.$Notify({
					title: 'Register Ok',
					type: 'success'
				})
				this.$router.push('/')
			}).catch(err => {
				// this.$router.push('/')
			})
		}
	}
}
</script>
<style lang="css" scoped>
body {
	background: #f8fafc;
}

.content {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

.login-container {
	background: white;
	position: relative;
	border-radius: 5px;
	display: flex;
	width: 70vw;
	height: 80vh;
	box-shadow: 0 19px 38px rgba(0, 0, 0, 0.07);
	transition: 0.4s;
}

.login {
	transition: 0.4s;
	font-family: Comfortaa;
}

.login#login-form {
	width: 50%;
	display: block;
	padding: 15px;
	align-items: center;
	text-align: center;
	position: relative;
	margin-top: 20px;
}

.login#login-bg {
	width: 50%;
	display: inline-flex;
	background: url(https://picsum.photos/700);
	background-size: cover;
	background-position: center center;
	float: right;
	border-radius: 0 5px 5px 0;
}

.register {
	width: 50%;
	display: block;
	padding: 15px;
	align-items: center;
	text-align: center;
	position: relative;
	margin-top: 30px;
	transition: 0.4s;
	font-family: Comfortaa;
	display: none;
}

.form-title {
	position: absolute;
	top: 0%;
	left: 0;
	right: 0;
	text-align: center;
	color: #dedede;
	font-size: 2.2em;
	font-family: Spartan;
	line-height: 1.4;
	margin-top: -24px;
}

.form-title hr {
	width: 75%;
	border: 2px solid #dedede1f;
	border-radius: 10px;
	margin-top: 20px;
}

.register .form-title {
	margin-top: -54px;
}

.form-container {
	padding: 25px 10px;
	border-radius: 5px;
	width: 80%;
	text-align: left;
	margin-top: 30px;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

label {
	font-size: 0.7em;
	color: #7392ff;
	margin: 0px 0 !important;
	margin-left: 4px !important;
}

input {
	background: #f8fafc;
	border: 0;
	width: 100%;
	display: block;
	padding: 10px 15px;
	border-radius: 5px;
	margin-bottom: 20px !important;
	outline: 0;
	transition: 0.4s;
}

input::placeholder {
	opacity: 0.4;
}

.submit-buttons {
	display: flex;
}

.submit-buttons input {
	width: 50%;
	text-align: center;
	color: white;
	transition: 0.2s;
}

.submit-buttons input:hover {
	opacity: 0.7;
}

.submit-buttons input:nth-of-type(1) {
	border-radius: 5px 0 0 5px;
	background: #54a0ff;
}

.submit-buttons input:nth-of-type(2) {
	border-radius: 0 5px 5px 0;
	background: #c8d6e5;
}

@media (max-width: 933px) {
	.login#login-bg {
		width: 0;
	}

	.login#login-form,
	.register {
		width: 100%;
	}

	input {
		width: 100%;
	}
}
</style>
