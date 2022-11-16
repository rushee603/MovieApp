import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { MovieserviceService } from 'src/app/services/movieservice.service';

@Component({
  selector: 'app-viewprofile',
  templateUrl: './viewprofile.component.html',
  styleUrls: ['./viewprofile.component.css']
})
export class ViewprofileComponent implements OnInit {

  constructor(private movieService:MovieserviceService,private router:Router,public dialog: MatDialog) { }
  
  email=localStorage.getItem("email") ;
  userName=localStorage.getItem("userName") ;
  password=localStorage.getItem("password") ;
  phoneNo:any ;
  user:any ;
  ngOnInit(): void {
    this.email=localStorage.getItem("email") ;
    this.movieService.getUser(this.email).subscribe(response=>{
      console.log(response.phoneNo);
      
        this.phoneNo=response.phoneNo
        console.log(this.phoneNo);
     
     
      
    })
  }
  ok(){
    this.dialog.closeAll();
  }



}
