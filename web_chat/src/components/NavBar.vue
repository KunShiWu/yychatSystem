<template>
    <nav class="navbar container " style="background-color: #e3f2fd; width: 100%;  "   v-if="$store.state.user.is_login" >
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container">
           
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">

                </button>
                <div class="collapse navbar-collapse" id="navbarText" style="box-sizing:border-box;">
                 <ul class="navbar-nav ">
                        <li class="nav-item">
                        <router-link class="nav-link " active-class="active" :to="{name:'home'}">个人首页</router-link>
                        </li>
                        <li class="nav-item">
                        <router-link class="nav-link " active-class="active" :to="{name:'space'}">好友空间</router-link>
                        </li>
                        <li class="nav-item">
                        <router-link class="nav-link"  active-class="active"  :to="{name:'linkman'}">好友列表</router-link>
                        </li>
                        <li class="nav-item">
                        <router-link class="nav-link"  active-class="active"  :to="{name:'message'}">好友消息</router-link>
                        </li>

     
                        <li class="nav-item dropdown" style="margin-left:188px;">
                          <div>
                            
                              <a class="nav-link dropdown-toggle " href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                          {{ $store.state.user.username }}
                         <div class="login_style" v-if="!$store.state.user.is_invisible_login"><span style="margin-left: 1px;"  >√</span></div>
                         <div class="logout_style " v-if="$store.state.user.is_invisible_login" > <span style="margin-left: 1.5px;">=</span></div>
                            </a>
                           
                            <ul class="dropdown-menu"  >
                                <li class="dropdown-item  dropdown-item-button"    @click="online">显示在线</li>
                                <li class="dropdown-item  dropdown-item-button"  @click="invisible">显示隐身</li>
                                <hr>
                                <li class="dropdown-item  dropdown-item-button" @click="logout">退出登录</li>
                            </ul>

                          </div>
                          
                          
                        
                         </li>
                      
                        </ul>
                       
                
             
               
    
                </div>
            </div>
        </nav>
    </nav>
</template>


<script>
import $ from "jquery"
import { useStore } from 'vuex';
import router from '@/router';
export default{

    setup(){
        const store=useStore();
        const logout =()=>{
            $.ajax({
                url:"http://localhost:7788/user/account/logout/",
                type:"POST",
                headers:{
                    Authorization:"Bearer "+store.state.user.access_token,
                   },
                   data:{
                    id:store.state.user.id
                   },
            })
            store.commit("logout");
 
            router.push({name:"login"})

          


        }

        const online =()=>{
            store.dispatch("Update_Is_Invisible_Login",{
                userid:store.state.user.id,
                status:"No"
            });
        }

        const invisible =()=>{
            store.dispatch("Update_Is_Invisible_Login",{
                userid:store.state.user.id,
                status:"Yes"
            });
        }

        return{
            logout,
            online,
            invisible
            
        }

    }
}
</script>

<style scoped>
.dropdown-item-button{
    cursor: pointer;
    user-select: none;

}

.login_style{
    display: inline-block;
    background-color:#08D969;
    width: 14px;
    height: 14x;
    border-radius: 50%;
    box-sizing: border-box;
    color: azure;
    font-weight: 100;
}

.logout_style{
    display: inline-block;
    background-color:#FFAA31;
    width: 14px;
    height: 14x;
    border-radius: 50%;
    box-sizing: border-box;
    color: azure;
    font-weight: 100;
}
</style>