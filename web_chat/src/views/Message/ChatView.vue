<template>
    <div class="card chatpage">
        <div class="card-body">
            <div class="chatpage_head " style="overflow:scroll;  overflow-x:auto ; overflow-y:auto ;height:300px; "  > 

                <ul  class="list-group">
                    <li v-for="(old_message) in old_messages" :key="old_message" >
                        <div class="chatpage_head_user" v-if="old_message.userid===$store.state.user.myfriend.friendid" style="margin-top:5px ;" >
                            <img class="chatpage_head_img" :src="$store.state.user.myfriend.photo" alt="">
                                <div class="card chatpage_head_user" style="margin-left: 10px;">
                                    <div class="card-body chatpage_head_user">
                                        <span>{{ old_message.message }}</span>
                                    </div>
                                </div>
                      </div> 
                      <div style="width: 15px;"></div>
                      <div  v-if="old_message.userid===$store.state.user.myfriend.userid" style="margin-top: 10px;"   >
                        <img class="chatpage_head_img float-end" :src="$store.state.user.photo" alt="" style="margin:15px 0 0 10px ; "  >
                            <div class="card chatpage_head_user float-end" style="background-color: #95EC69; ">
                                <div class="card-body chatpage_head_user">
                                    <span>{{old_message.message}}</span>
                                </div>
                              </div>
                          </div> 
                    </li>

                    <li>
                     

                    </li>
                </ul>
              
                

            </div>
           
              <div class="chatpage_body">
                <form  @submit.prevent="SendMessage " >
                    <div class="form-floating">
                        <textarea v-model="message" class="form-control chatpage_body_type" ></textarea> 
                        </div>
                        <div class="chatpage_body_btn">
                        <button type="submit" class="btn btn-outline-info float-end" >发送</button>
                    </div>
                </form>
              </div>

        </div>
    </div>
 
</template>

<script> 

import $ from 'jquery'
import { useStore } from 'vuex';
import { ref } from 'vue';
export default {
    name:"ChatView",
    setup(){

        const store=useStore();
        const myfriend=store.state.user.myfriend;
        let message=ref();

        let old_messages=ref([]);

        const GetMessage =() =>{
            if(store.state.user.access_token)
            $.ajax({
                url:"http://localhost:7788/user/message/getmessage/",
                type:"GET",
                headers:{
                    Authorization:"Bearer "+store.state.user.access_token,
                    },
                    data:{
                        userid:store.state.user.id,
                        friendid:store.state.user.myfriend.friendid,
                    },
                    success(resp)
                    {
                        old_messages.value=resp;
                    },
                    error(){
                      
                    }
                    
            })
        }
       
        if(store.state.user.access_token)  GetMessage();
        const SendMessage = ()=>{
            if(store.state.user.access_token)
{
    $.ajax({
                url:"http://localhost:7788/user/message/addmessage/",
                type:"POST",
                headers:{
                    Authorization:"Bearer "+store.state.user.access_token,
                    },

                data:{
                    userid:store.state.user.id,
                    friendid:myfriend.friendid,
                    message:message.value,
                },
                success()
                {
                message.value='';
                    GetMessage();
                }
                
            });
}
   
        }
        if(store.state.user.access_token)
        setInterval(()=>{
            GetMessage();
        },1000);
        return {
            message,
            old_messages,
            SendMessage,
            GetMessage
        }
    }
}
</script>

<style scoped>
.chatpage{
    height: 520px;
    margin-top: 20px;
    box-sizing: border-box;
}
.chatpage_head{
    height: 70%;
}
.chatpage_body{
    height: 30%;
}

.chatpage_body_type{
    margin-top: 50px;
    height: 100px;
    width: 100%;

}
.chatpage_body_btn{
    margin-top: 5px;
}
.chatpage_head_img{
    width: 25px;
    border-radius: 50%;
    
}

.chatpage_head_user{
    display: inline-block;
}



</style>