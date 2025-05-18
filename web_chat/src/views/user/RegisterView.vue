<template>
 <div class="user-card">
        <div class="user-card-head"></div>
        <div class="user-card-body">
            <div class="user-card-body-content">

                <form @submit.prevent="REGISTER">
                    <label for="exampleInputEmail1" class="form-label container" v-if="!register_way"  style="margin-left: 160px; color:black;"  >选择注册方式</label>
                    <div v-if="register_way" style="margin-top: 10px;"></div>
                    <div class="mb-3" style="margin-left: 110px; ">
                    <button type="button" class="btn btn-outline-info btn-sm " @click=" click_register_way('yy')"  >YY号码</button>
                    <button type="button" class="btn btn-outline-info btn-sm" style="margin-left: 10px;" @click=" click_register_way('phone')">手机号码</button>
                    <button type="button" class="btn btn-outline-info btn-sm" style="margin-left: 10px;" @click=" click_register_way('email')">电子邮箱</button>
                </div>
                <div class="mb-2">
                    <input v-model="username" type="text" class="form-control" id="exampleInputPassword1" v-if="register_way==='yy'" style="width:60%; margin-left: 110px;"  placeholder="输入yy号码注册">
                    <input v-model="username" type="text" class="form-control" id="exampleInputPassword1" v-else-if="register_way==='phone'" style="width:60%; margin-left: 110px;"   placeholder="输入手机号码注册">
                    <input v-model="username" type="email" class="form-control" id="exampleInputPassword1" v-else-if="register_way==='email'" style="width:60%; margin-left: 110px;"   placeholder="输入电子邮箱注册">                    
                </div>
                
                <div class="mb-2">
                    <input v-model="password" type="password" class="form-control" id="exampleInputPassword1" v-if="register_way" style="width:60%; margin-left: 110px;"  placeholder="设置登录密码">
                </div>
                <div class="mb-2">
                    <input v-model="confirmPassword" type="password" class="form-control" id="exampleInputPassword1" v-if="register_way" style="width:60%; margin-left: 110px;"  placeholder="确认登录密码">
                </div>
                <button type="submit" class="btn btn-primary btn-sm" v-if="register_way"  style="margin-left: 110px; width: 60%;">注册</button>
                <div class="mb-2">
                    <span style="margin-left:110px ; color: red;">{{ error_message }}</span>
                </div>
               
                </form>
            </div>
       
        </div>        
    </div>
    
</template>

<script>

import { ref } from 'vue';
import router from '@/router';
import $ from "jquery"
export default{
    name:"RegisterView",
    setup(){
        let register_way =ref();
        let username=ref();
        let password=ref();
        let confirmPassword=ref();
        let error_message=ref();
        const click_register_way =(Way)=>{
            register_way.value=Way;
        }

        const REGISTER =()=>{
            error_message.value='';
            $.ajax({
                url:"http://localhost:7788/user/account/register/",
                type:"post",
                data:{
                    username:username.value,
                    password:password.value,
                    confirmPassword:confirmPassword.value,
                },
                success(resp)
                {
                    if(resp.error_message==='success')
                  router.push({name:"login"});
                  else  {
                    error_message.value=resp.error_message;
                    username.value='';
                    password.value='';
                    confirmPassword.value='';
                  }
                },
                error(resp){
                    error_message.value=resp.error_message;
                }

            });
        }

        return {
            click_register_way,
            register_way,
            username,
            password,
            confirmPassword,
            error_message,
            REGISTER
        }
    }
}

</script>

<style scoped>

.user-card {
    width: 450px;
    height: 360px;
    box-shadow: 2px 2px 5px lightgrey;
    border-radius: 5px;
    box-sizing: border-box;
    margin: 50px auto;
}

.user-card-head {
    width: 100%;
    height: 30%;
    background-size: cover;
    background-image: url("https://cdn.acwing.com/media/article/image/2022/03/01/1_3ec4c1cf99-mountain.jpg");

}

.user-card-body {
    width: 100%;
    height: 75%;
    box-sizing: border-box;
}



.user-card-body-content {
    width: calc(100% - 70px);
    height: 100%;
    float: left;

}

.input_size{
    width: 70%;
}


.font-style{
    color: gray;
    font-size: 12px;
    margin-left: 5px;
    user-select: none;
}

</style>