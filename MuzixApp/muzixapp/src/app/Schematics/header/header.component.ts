import { Component } from '@angular/core';
import {MatDialog} from '@angular/material/dialog'
import { RegisterComponent } from 'src/app/Components/register/register.component';
import { LoginComponent } from 'src/app/Components/login/login.component';
import { Router } from '@angular/router';
import { MovieserviceService } from 'src/app/services/movieservice.service';
import { Movie } from 'src/app/classes/movie';
import { NgToastService } from 'ng-angular-popup';
import { AuthenticationserviceService } from 'src/app/services/authenticationservice.service';
import { ViewprofileComponent } from 'src/app/Components/viewprofile/viewprofile.component';
import { EditprofileComponent } from 'src/app/Components/editprofile/editprofile.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent{

  constructor(public dialog: MatDialog,private router:Router,private movieService:MovieserviceService,private authService:AuthenticationserviceService,private toast :NgToastService) {}
  isLoggedIn=false;
  // isLoggedIn=this.authService.isLoggedIn ;
  userNameFromLogInComponent:string=this.authService.username;

    movieName:any;
    movies:Movie[]=[];
    
    openDialog() {
      const dialogRef = this.dialog.open(RegisterComponent);
      dialogRef.afterClosed().subscribe(result => {
      });
    }
    openDialog1() {
      const dialogRef = this.dialog.open(LoginComponent);
      dialogRef.afterClosed().subscribe(result => {
      });
    }

    navigateToTreandingMovies(){
      this.router.navigate(['trendingmovies'])
    }
    navigateToTopRatedMovies(){
      this.router.navigate(['topratedmovies'])
    }   
    navigateToPopularMovies(){
      this.router.navigate(['popularmovies'])
    }
    
    favMovie:any;
    email:any ;
    favouriteListMovies:any;
    existingId:any;
    searchMovieBasedOnId(id:any){
  
      this.email=this.movieService.emailOfLoggedInUser ;
      console.log(this.email);
      
      this.movieService.showtrendingMovies(2).subscribe(res=>{
        this.favMovie=res.results.filter(x=>x.id==id) ;
        this.addToFavouriteList(this.email,this.favMovie[0])
      })
    }
  
    addToFavouriteList(email:any,movie:any){
      this.movieService.addMovieToFavouritelist(email,movie).subscribe(res=>{
        console.log(res);
        
        this.toast.success({detail:"MovieAdded...",summary:"Movie Added To Your FavouriteList !!",duration:5000})
      })
    }

    searchMovie(){
      this.movieService.searchMovieBasedOnName(this.movieName).subscribe(res=>{
        this.movies=res.results ;
    })
    }
    name:any ;
    ngOnInit(){

      this.isLoggedIn=this.authService.isLogin() ;
      this.name=localStorage.getItem("userName")?.toUpperCase() ;
    }

    signOut(){
      this.authService.isLogOut();
      this.router.navigate([""]);
      location.reload();
    }

    movieId:any;

    recommendMoviesbasedOnId(){
      this.router.navigate(['recommendmovies']);
    }

    callFavComp(){
      this.router.navigate(['favmovies'])
    }

    viewProfile(){
      this.dialog.open(ViewprofileComponent) ;
      
    }
    editProfile(){
      this.dialog.open(EditprofileComponent) ;
    }

  }
 
 
            
 



