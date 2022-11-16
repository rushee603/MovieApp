import { Component, OnInit } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationserviceService } from 'src/app/services/authenticationservice.service';
import { NavigationService } from 'src/app/services/navigation.service';
import {MatDialog} from '@angular/material/dialog'
import { NgToastService } from 'ng-angular-popup';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registration:UntypedFormGroup = new UntypedFormGroup({
    email: new UntypedFormControl('', [Validators.required]),
    userName: new UntypedFormControl('', [Validators.required]),
    password: new UntypedFormControl('',[Validators.minLength(5),Validators.maxLength(10)]),
    cpassword: new UntypedFormControl('',[Validators.minLength(5),Validators.maxLength(10)]),
    phoneNo: new UntypedFormControl("", [Validators.required, Validators.pattern("[1-9][0-9]{9}")])
  })
  get email() {return this.registration.get("email")}
  get password() {return this.registration.get("password")}
  get userName() {return this.registration.get("userName")} 
  get phoneNo() {return this.registration.get("phoneNo")}

  constructor(private service:AuthenticationserviceService, private route : Router, private nav: NavigationService,public dialog: MatDialog,private toast :NgToastService) { }

  submit() 
  { 
    const data=this.registration.value
    this.toast.success({detail:"REGISTERED😊",summary:"Registered mail 📧 sent successfully !!",duration:5000}) ;
    console.log(data);
    this.dialog.closeAll();
    this.service.register(data).subscribe(res=>{
      console.log(res);
      this.dialog.open(LoginComponent) ;
    })
    
  }

  ngOnInit(): void { }


}
