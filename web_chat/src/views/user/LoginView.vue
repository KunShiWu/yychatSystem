<template>
 
 <div class="user-card">
        <div class="user-card-head"></div>
        <div class="user-card-body">
            <div class="user-card-body-content">
                <div style="margin:5px 0 5px 112px; ">
                    <label for="exampleInputEmail1" class="form-label container" style="margin-left: 50px; color:black;" v-if="!login_way"  >选择登录方式</label>
                    <button type="button" class="btn btn-outline-info btn-sm " @click=" click_login_way('yy')"  >YY号码</button>
                    <button type="button" class="btn btn-outline-info btn-sm" style="margin-left: 10px;" @click=" click_login_way('phone')">手机号码</button>
                    <button type="button" class="btn btn-outline-info btn-sm" style="margin-left: 10px;" @click=" click_login_way('email')">电子邮箱</button>
                </div>

                <form class="container" style="margin:0 0 0 35px ;" @submit.prevent="LOGIN" >
                <div class="mb-2">
                    <input v-model="username"  type="text" class="form-control input_size container" v-if="login_way==='yy'" placeholder="请输入yy号码登录">
                    <input  v-model="username"   type="text" class="form-control input_size container" v-else-if="login_way==='phone'" placeholder="请输入手机号码登录">
                    <input  v-model="username"   type="email" class="form-control input_size container" v-else-if="login_way==='email'"  placeholder="请输入电子邮箱登录" aria-describedby="emailHelp" >
                </div>
                <div v-if="login_way">
                    <div class="mb-2">
                    <input  v-model="password"  type="password" class="form-control input_size container" id="exampleInputPassword1" placeholder="请输入密码" >
                </div>
            
                    <div class="row" style="margin-left: 20px;">
                        <div class="col-4" ><input @click="is_invisible_login"  type="checkbox" class="form-check-input " id="exampleCheck1" ><span class="font-style">隐身登录</span></div>
                                                                                                                                          
                        <div class="col-4"><input  type="checkbox" class="form-check-input" id="exampleCheck1"><span class="font-style" >记住密码</span></div>
                        <div class="col-4"><span class="font-style" style="cursor: pointer;" >找回密码</span></div>
                    </div>
                    <button type="submit" class="btn btn-info btn-sm " style="margin-left:50px ;width: 35%; color: white;" >登录</button>
                    <button type="submit" class="btn btn-outline-secondary btn-sm " @click="register"   style="margin-left: 2px; width: 35%;" >注册</button>
                </div>
          
             
                <span style="margin-left:80px ;color: red;">{{error_message }}</span>
                </form>
            </div>
       
        </div>        
    </div>
    
</template>

<script>
import { ref } from 'vue';
import router from '@/router';
import { useStore } from 'vuex';
export default{
    name:"LoginView",
    setup(){
        const store=useStore();
        let login_way=ref();
        let username=ref('');
        let password=ref('');
        let error_message=ref();
        let is_invisible_login_flag=0;
        const click_login_way=(loginWay)=>{
            login_way.value=loginWay;
        }
        const register =()=>{
            router.push({name:"register"})
        }

        const LOGIN=()=>{
            error_message.value='';

            store.dispatch('login',{
                username:username.value,
                password:password.value,
                success(){
                    store.dispatch("getuserInfo",{
                        success(){
                            router.push({name:"home"});
                            if(is_invisible_login_flag % 2 !==0)
                            {
                                store.dispatch("Update_Is_Invisible_Login",{
                         
                                    userid:store.state.user.id,
                                    status:"Yes",
                                });
                            }
                            else {
                                store.dispatch("Update_Is_Invisible_Login",{
                                    userid:store.state.user.id,
                                    status:"No",
                                });
                            }
                        
                        },
                        error(){
                            error_message.value="用户名或者密码错误";
                        }
                    })
                   
                },
                error(){
                    error_message.value="用户名或者密码错误";
                }
            });
        }
        const is_invisible_login =()=>{
            is_invisible_login_flag++;
        }

        return {
            login_way,
            click_login_way,
            register,
            username,
            password,
            error_message,
            LOGIN,
            is_invisible_login
        }
    }
}

</script>

<style scoped>

.user-card {
    width: 450px;
    height: 300px;
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