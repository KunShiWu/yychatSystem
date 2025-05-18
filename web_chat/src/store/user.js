import $ from "jquery"

export default ({
    state: {
        is_login:false,
        is_invisible_login:false,
        is_remember_password:false,
        id:"",
        username:"",
        photo:"",
        title:"",
        access_token:"",
        myfriend:'',
        is_valid:false,
        
    },
    getters: {
    },
    mutations: {
        UpdateFriendInfo(state,info)
        {
            state.myfriend=info;
        },
        UpdateUserInfo(state,info)
        {
            state.id=info.id;
            state.username=info.username;
            state.photo=info.photo;
            state.is_login=info.is_login;
            state.title=info.title;
        },
        UpdateAccessToken(state,access_token)
        {
            state.access_token=access_token;
        },
        is_jwtToken_vaild(state,is_valid)
        {
            state.is_valid=is_valid;
        },
        logout(state)
        {
            localStorage.removeItem("jwt_token");
            state.is_login=false;
            state.is_invisible_login=false;
            state.id='';
            state.username='';
            state.photo='';
            state.title='';
            state.access_token='';
            state.myfriend='';
            state.is_valid=false;
        },
        Update_is_invisible_login(state,flag)
        {
            if(flag%2===0)
            {
                state.is_invisible_login=false;

            }
            else {
                state.is_invisible_login=true;

            }
        },
      
    },
    actions: {
        login(context,data)
        {
           $.ajax({
                url:"http://localhost:7788/user/account/token/",
                type:"post",
                data:{
                username:data.username,
                password:data.password,
                },
                success(resp){
                    if(resp.error_message==="success")
                    {
                        localStorage.setItem("jwt_token",resp.access_token);
                        context.commit("UpdateAccessToken",resp.access_token);
                        data.success();
                    }
                    else{
                        data.error();
                    }
                },
                error(){
                    data.error();
                }
            
            });
        },
        getuserInfo(context,data)
        {
            $.ajax({
               url:"http://localhost:7788/user/account/info/",
                type:"GET",
                headers:{
                Authorization:"Bearer "+context.state.access_token,
                },
                success(resp)
                {
                
                    if(resp.error_message==="success")
                    {
                        context.commit("UpdateUserInfo",{
                            ...resp,
                            is_login:true,
                        });
                      
                        data.success(resp);
                    }
                },
                error()
                {
                    data.error();
                }
            })
        },

        Update_Is_Invisible_Login(context,data)
        {
          $.ajax({
            url:"http://localhost:7788/user/account/getaccountstatus/",
            type:"POST",
            headers:{
                Authorization:"Bearer "+context.state.access_token,
                },
            data:{
                userid:data.userid,
                status:data.status,
            },
            success(resp)
            {
                if(resp.error_message==="Yes")
                {
                    context.commit("Update_is_invisible_login",1);
                }
                else 
                {
                    context.commit("Update_is_invisible_login",2);
                }
            }
          })
        }

    },
    modules: {
    }
})