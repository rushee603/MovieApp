import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationserviceService {

  constructor(private httpclient:HttpClient,private router:Router, private toast :NgToastService) { }

  registerurl="http://localhost:9000/usermovie/register"

  loginurl="http://localhost:9000/user/login"

  register(data:any)
  {
    return this.httpclient.post(this.registerurl,data)
  }

  logindata(data:any){
    console.log(data);
    return this.httpclient.post(this.loginurl,data)
  }
  
  loginUser(token:any, userName:any,email: any,password:any){
    localStorage.setItem("token",token);
    localStorage.setItem("userName",userName);
    localStorage.setItem("email",email);
    localStorage.setItem("password",password) ;
    sessionStorage.setItem("login",'true');
    location.reload() ;
    this.toast.success({detail:"SIGGNEDIN😊",summary:"SignIN Successfull !!",duration:10000})
    return true;
  }

  isLoggedIn: boolean = false;

  isLogin(){
    
    let token=localStorage.getItem("token");
    if(token==undefined || token=='' || token==null){
      return false;
    }else{
      return true;
    }
  }

  isLogOut(){
    localStorage.removeItem('token');
    localStorage.removeItem('userName');
    return  true;
  }
      
  username:any="";

  userName(data:any){
    this.username=data ;
  }

  

  getEmail(data:any){
    return this.httpclient.post(`http://localhost:9000/user/getemail`,data,{responseType:'text'})
  }


  

}
