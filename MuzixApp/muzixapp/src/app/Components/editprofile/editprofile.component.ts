import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormControl, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { NgToastService } from 'ng-angular-popup';
import { MovieserviceService } from 'src/app/services/movieservice.service';

@Component({
  selector: 'app-editprofile',
  templateUrl: './editprofile.component.html',
  styleUrls: ['./editprofile.component.css']
})
export class EditprofileComponent implements OnInit {
  registration:UntypedFormGroup = new UntypedFormGroup({
    password: new UntypedFormControl('',[Validators.minLength(5),Validators.maxLength(10)]),
    cpassword: new UntypedFormControl('',[Validators.minLength(5),Validators.maxLength(10)]),
    phoneNo: new UntypedFormControl("", [Validators.required, Validators.pattern("[1-9][0-9]{9}")])


  })
  
  get password() {return this.registration.get("password")}
  get phoneNo() {return this.registration.get("phoneNo")}

  constructor(private movieService:MovieserviceService,public dialog: MatDialog, private toast :NgToastService) { }

  userName=localStorage.getItem("userName")?.toUpperCase() ;

  ngOnInit(): void {

  }

  email:any ;
  update(){
    this.email=localStorage.getItem("email") ;
    console.log(this.email);
    this.movieService.updateUser(this.email,this.registration.value).subscribe(response=>{
      console.log(response);
      this.toast.success({detail:"Updated Successfully ✅",duration:5000})
      this.dialog.closeAll();
    }) ;
  }

}
