<template>


        <div class="dropdown" style="margin-top: 5px;">
            <button  type="button"  class="btn btn-outline-primary " data-bs-toggle="dropdown" aria-expanded="false" style="width: 100%;">
                我的好友
            </button>
     
            <ul class="dropdown-menu"  style="overflow:scroll;  overflow-x:auto ; overflow-y:auto ;height:250px;width: 100%; ">
              <div v-if="!friends[0]" style="margin-left: 38%; color:cornflowerblue; user-select: none; "   >快去加陌生人为好友吧</div>
                <li  class="dropdown-item" v-for="(friend) in friends" :key="friend.friendid"  >
                    <div class="row" >
                      <div class="col-3" >
                        <img :src="friend.photo" alt="" class="img_style">
                        <span v-if=" IsInvisible[friend.friendid]==='No' && IsLogin[friend.friendid]==='Yes'"  class="login_style "  >在线</span>
                        <span v-else-if="IsInvisible[friend.friendid]==='Yes'|| IsLogin[friend.friendid]==='No'" class="logout_style "  >离线</span>
                      </div>
    
                      <div class="col-6"> {{ friend.title }}</div>
                      <div class="col-2"> 
                        <button style="margin-left: 50px;" class="btn btn-outline-success" v-if="friend.status==='allFriend'"  @click="send_message(friend)">发消息</button>
                        <div style="margin-right: 150px;" v-if="friend.status==='oneFriend'">等待对方添加为好友</div>
                      </div>
                     
                    </div>
                </li>
            </ul>

            </div>


            <div class="dropdown">
                <button type="button" class="btn btn-outline-secondary " data-bs-toggle="dropdown" aria-expanded="false" style="width: 100%; margin-top: 50%;" >陌生人</button>
                 <ul class="dropdown-menu"   style="overflow:scroll;  overflow-x:auto ; overflow-y:auto ;height:250px;width: 100%; ">
                    <li class="dropdown-item"   v-for="(Stranger) in Strangers"  :key="Stranger.id   "  >
                     
                    <div class="row" v-if="Stranger.id!==userId " style="margin-top: 10px;">
                        <div class="col-3">
                            <img :src="Stranger.photo" alt="" class="img_style">
                        </div>
                        <div class="col-7">
                            {{ Stranger.username }}
                        </div>
                        <div class="col-2">
                          <button type="button" class="btn btn-light" style="margin-top: 2px;" @click="AddFriend(Stranger)" >+好友</button>
                        </div>
                    </div>
                    
                    
                    </li>
                   
                </ul>
            </div>


    
</template>

<script>
import $ from "jquery"
import { useStore } from "vuex";
import { ref } from "vue";
import router from "@/router";
export default{
    name:"LinkManView",
    setup(){
        const store=useStore();
        let Strangers=ref([]);
        let friends=ref([]);
        let userId=parseInt( store.state.user.id);
        let IsInvisible=ref([]);
        let IsLogin=ref([]);
        const getStrangerInfo =()=>{
        
          $.ajax({
            url:"http://localhost:7788/user/friendlist/stranger/info/",
            type:"GET",
            headers:{
              Authorization:"Bearer "+store.state.user.access_token,
            },
            success(resp)
            {
            Strangers.value=resp;
        
            },
            error(resp)
            {
              console.log(resp);
            }

            
          });
        }
        getStrangerInfo();
    

        const getFriendsInfo=()=>{
          $.ajax({
                url:"http://localhost:7788/user/friendlist/friends/info/",
                type:"GET",
                headers:{
                  Authorization:"Bearer "+store.state.user.access_token,
                },
                success(resp)
                {
                  friends.value=resp;
                }  
              });
        }
        getFriendsInfo();

      const AddFriend = (Stranger) =>{
    

        $.ajax({
          url:"http://localhost:7788/user/friendlist/isFriend/",
          type:"POST",
          data:{
            userid:userId,
            friendid:Stranger.id,
          },
          headers:{
          Authorization:"Bearer "+store.state.user.access_token,
            },
   
        })

     
        $.ajax({
          url:"http://localhost:7788/user/friendlist/add/",
          type:"POST",
          data:{
            friendid:Stranger.id,
            userid:store.state.user.id,
            title:Stranger.title,
            photo:Stranger.photo
          },
          headers:{
          Authorization:"Bearer "+store.state.user.access_token,
            },

            success()
            {
              getStrangerInfo();
              getFriendsInfo();
            },
            error(resp)
            {
              console.log(resp);
            }


        })
      }

      const send_message =(friend)=>{
        store.commit("UpdateFriendInfo",friend);
        router.push({name:'chat'})
      }


  
       
      const Get_Is_invisible=()=>{
        if(store.state.user.access_token)
      {
        $.ajax({
          url:"http://localhost:7788/user/friendlist/isinvisible/",
          type:"POST",
          headers:{
          Authorization:"Bearer "+store.state.user.access_token,
            },
            success(resp)
            {
              IsInvisible.value=resp;
            }
        })  

      }

   

      }


      const Get_Is_login=()=>{
        if(store.state.user.access_token){
          $.ajax({
            url:"http://localhost:7788/user/account/getLogin/",
            type:"GET",
            headers:{
          Authorization:"Bearer "+store.state.user.access_token,
            },
            success(resp)
            {
              IsLogin.value=resp;
            }
          })
        }

        }

        if(store.state.user.access_token){
          setInterval(()=>{
        Get_Is_invisible();
        Get_Is_login();
        
        },1000);
        }

    

      return{
        Strangers,
        AddFriend,
        userId,
        friends,
        send_message,
        IsInvisible,
        Get_Is_login,
        IsLogin
      }

    }
}

</script>

<style scoped>
.img_style{
    width: 50px;
    border-radius: 50%;
}
.login_style{
  font-size: 12px;
  margin: 50px   0 0 5px ;
  color: #01fb7e;
  user-select: none;
}

.logout_style{
  font-size: 12px;
  margin: 50px  0 0 5px ;
  color: #ADADAD;
  user-select: none;
}
</style>