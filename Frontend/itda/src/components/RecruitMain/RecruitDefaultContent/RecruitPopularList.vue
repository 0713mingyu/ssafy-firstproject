<template>
  <v-col class="wantedview" cols="12" md="6">
    <h2 class="mb-3">요즘 핫한 공고들이에요!</h2>
    <v-carousel v-if="wantedlist.length" hide-delimiters :show-arrows="false" cycle interval="2500" height="400">
      <v-carousel-item v-for="j in 2" :key="j">
        <v-container white>
          <v-row id="wanted" v-for="i in 5" :key="i" style="height:78px;" @click="goDetailPage(wantedlist[5*(j-1)+i-1].wanted.wid)">
            <v-col cols="3">
              <v-img v-if="wantedlist[5*(j-1)+i-1].company.logo" :src="wantedlist[5*(j-1)+i-1].company.logo" :alt="wantedlist[5*(j-1)+i-1].company.corpNm" :contain="true" max-width="150" aspect-ratio="2.67"></v-img>
              <v-img v-else :src="getImgUrl('noimg.png')" alt="noimg" max-width="150" aspect-ratio="2.67" :contain="true"></v-img>
              </v-col>
            <v-col cols="9">
              <v-row id="corpNm">{{wantedlist[5*(j-1)+i-1].company.corpNm}}</v-row>
              <v-row id="wantedTitle">{{wantedlist[5*(j-1)+i-1].wanted.wantedTitle}}</v-row>
            </v-col>
          </v-row>
        </v-container>
      </v-carousel-item>
    </v-carousel>
  </v-col>
</template>

<script>
import axios from 'axios'
import router from '../../../router'


export default {
  name: "recruitpopularlist",
  methods:{
		getWantedList(){
			axios.get(`http://192.168.31.54:8197/itda/api/getWantedByView/`)
				.then(response=>{
					this.wantedlist = response.data
				})
				.catch(()=>{})
    },
    getImgUrl(img) {
      return require('../../../assets/' + img)
    },
    goDetailPage(wid){
      router.push({name:'recruitdetail',params:{id:wid}})
    }
	},
	mounted(){
		this.getWantedList()
	},
  data() {
    return {
      wantedlist:[],
      // today:Date().getTime()
    }
  }
}
</script>

<style>
#corpNm{
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
}
#wantedTitle{
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
}
#wanted:hover{
  background-color: #eeeeee;
}
</style>