import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Movie } from 'src/app/classes/movie';
import { AuthenticationserviceService } from 'src/app/services/authenticationservice.service';
import { MovieserviceService } from 'src/app/services/movieservice.service';

@Component({
  selector: 'app-trendingmovies',
  templateUrl: './trendingmovies.component.html',
  styleUrls: ['./trendingmovies.component.css']
})
export class TrendingmoviesComponent implements OnInit {

  constructor(private movieService:MovieserviceService, private router:Router,private authService:AuthenticationserviceService,private toast :NgToastService) { }

  movie:Movie[]=[];
  p:number=1;
  count: number = 10;
  value:number=5


  ngOnInit(): void {
    this.isLoggedIn=this.authService.isLogin() ;
    this.movieService.showtrendingMovies(4).subscribe(res=>{
      this.movie=res.results;
    })
  }

  isLoggedIn=false;



  favMovie:any;
  email:any ;
  searchMovieBasedOnId(id:any){
    this.movieService.showtrendingMovies(4).subscribe(res=>{
      this.favMovie=res.results.filter(x=>x.id==id) ;
      console.log(this.favMovie[0]);
      this.email=localStorage.getItem("email") ;
      this.addToFavouriteList(this.email,this.favMovie[0])
    })
  }

  

  addToFavouriteList(email:any,movie:any){
    this.movieService.addMovieToFavouritelist(email,movie).subscribe(res=>{
      this.toast.success({detail:"MovieAdded...",summary:"Movie Added To Your FavouriteList !!",duration:5000})
      
    })
  }

  callFavComp(){
    if(this.isLoggedIn){
      this.router.navigate(['favmovies'])
   }
   else{
     this.toast.warning({detail:"Need to SignIn ⚠️",summary:"SignIn to get access for favourte Movies!!",duration:5000})
    }
  }

  navigateToTopRatedMovies(){
    this.router.navigate(['topratedmovies'])
  }   
  navigateToPopularMovies(){
    this.router.navigate(['popularmovies'])
  }

}
