<template>
  <div class="card container" style="width:50%; height:800px;">
    <div class="card-body ">
      <NavBar />
      <router-view/>
    </div>
  </div>

</template>

<script>
import "bootstrap/dist/css/bootstrap.css"
import "bootstrap/dist/js/bootstrap"
import NavBar from '@/components/NavBar'
// import $ from "jquery"
export default{
    name:"App",
    components:{
      NavBar
    },

}

</script>

<style scoped>

</style>
