import { Component, OnInit } from '@angular/core';
import { Validators, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationserviceService } from 'src/app/services/authenticationservice.service';
import { NavigationService } from 'src/app/services/navigation.service';
import {MatDialog} from '@angular/material/dialog'
import { NgToastService } from 'ng-angular-popup';
import { MovieserviceService } from 'src/app/services/movieservice.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  loginform=new FormGroup({
    userName:new FormControl('',[Validators.required]),
    password: new FormControl("",[Validators.minLength(5),Validators.maxLength(10)])
   })
   get userName() {return this.loginform.get("userName")}
   get password() {return this.loginform.get("password")}

  constructor(private authservice:AuthenticationserviceService, private navigate:NavigationService, private route :Router,public dialog: MatDialog, private toast :NgToastService,private movieService:MovieserviceService) { }
  ngOnInit(): void {
   
  }
hide=true;
  isLoggedIn:boolean=false;

  login(){
    const data=this.loginform.value
    console.log(data);
    
    this.authservice.logindata(data).subscribe((x:any)=>{
      console.log(x);
      this.authservice.loginUser(x.token,x.userName,x.email,x.password);
      this.dialog.closeAll();

      console.log(localStorage.getItem("email"));
      
    },
      ()=>{
      this.toast.error({detail:"FAILED😢",summary:"Login  is Faild Try Again Later!!",duration:5000})
      
})}


}



















  //     this.auth.isAuth=true;
  //   if(this.auth.redirect!==null){
      
  //     this.route.navigate([this.auth.redirect])
  //   }
  // else
      //using service to store log in nd log out details
      // localStorage.setItem("isLoggedIn",true) ;

      // this.authservice.isLogin();