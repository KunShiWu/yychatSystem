import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/user/LoginView' 
import RegisterView from '@/views/user/RegisterView'
import HomeView from '@/views/Home/HomeView'
import MessageView from '@/views/Message/MessageView'
import SpaceView from '@/views/Space/SpaceView'
import LinkManView from '@/views/user/LinkManView'
import store from '@/store'
import ChatView from '@/views/Message/ChatView'

const routes = [
{
  path:"/",
  name:"home",
  component:HomeView,
  meta:{
    requestAuth:true,
  }
},
{
  path:"/space/",
  name:"space",
  component:SpaceView,
  meta:{
    requestAuth:true,
  }
},
{
  path:"/linkman/",
  name:"linkman",
  component:LinkManView,
  meta:{
    requestAuth:true,
  }
},
{
  path:"/message/",
  name:"message",
  component:MessageView,
  meta:{
    requestAuth:true,
  }
},

{
  path:"/login/",
  name:"login",
  component:LoginView,
  meta:{
    requestAuth:false,
  }
},
{
  path:"/register/",
  name:"register",
  component:RegisterView,
  meta:{
    requestAuth:false,
  }
},

{
  path:"/chat/",
  name:"chat",
  component:ChatView,
  meta:{
    requestAuth:true,
  }
}

]

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach((to,from,next)=>{


    const jwt_token=localStorage.getItem("jwt_token");
    if(jwt_token)
    {
        store.commit("is_jwtToken_vaild",true);
        store.commit("UpdateAccessToken",jwt_token);

        store.dispatch("getuserInfo",{
          success(){
            next();
          },
          error(){
            store.commit("is_jwtToken_vaild",false);
            next({name:"login"});
          }
        })
    }
    
    if(to.meta.requestAuth && !store.state.user.is_login)
    {
      if(store.state.user.is_valid)//如果是已经登录了就不用跳转
      {
        next();
      }
      else{
        store.commit("is_jwtToken_vaild",false);
        alert("请重新登录!");
        next({name:"login"});
      }
    }
    else next();
});

export default router
