import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Movie } from 'src/app/classes/movie';
import { Result } from 'src/app/classes/result';
import { AuthenticationserviceService } from 'src/app/services/authenticationservice.service';
import { MovieserviceService } from 'src/app/services/movieservice.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  
  isLoggedIn=false;
constructor(private movieService:MovieserviceService,private authService:AuthenticationserviceService, private router:Router,private toast :NgToastService) {
}

trendingMovies:Result=new Result();

movie:Movie[]=[];
  p:number=1;
  count: number = 10;

  ngOnInit(): void {
    this.isLoggedIn=this.authService.isLogin() ;
    this.movieService.showtrendingMovies(2).subscribe(res=>{
      this.movie=res.results;
    });   
  }

  favMovie:any;
  email:any ;
  favouriteListMovies:any;
  existingId:any;

  searchMovieBasedOnId(id:any){
    this.email=localStorage.getItem("email")
    this.movieService.showtrendingMovies(2).subscribe(res=>{
      this.favMovie=res.results.filter(x=>x.id==id) ;
      this.addToFavouriteList(this.email,this.favMovie[0])
    })
  }

  addToFavouriteList(email:any,movie:any){
    this.movieService.addMovieToFavouritelist(email,movie).subscribe(response=>{
      if(response.status==208){
        this.toast.warning({detail:"Movie Not Added",summary:"Movie Already Exist in Your FavouriteList !!",duration:5000})
      }
      else{
        this.toast.success({detail:"MovieAdded✅",summary:"Movie Added To Your FavouriteList !!",duration:5000})
      }
     
    }
    
    )
  }

  callFavComp(){
    if(this.isLoggedIn){
       this.router.navigate(['favmovies'])
    }
    else{
      this.toast.warning({detail:"Need to SignIn ⚠️",summary:"SignIn to get access for favourte Movies!!",duration:5000})
     }
   
  }
  recommendMovies(){
    this.router.navigate(['recommendmovies']);
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
  
  sound(){
  let audio= new Audio();
  audio.src="./assets/sound.mp3";
  audio.load();
  audio.play();
  }
 
}


