import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { MovieserviceService } from 'src/app/services/movieservice.service';

@Component({
  selector: 'app-favouritemovielist',
  templateUrl: './favouritemovielist.component.html',
  styleUrls: ['./favouritemovielist.component.css']
})
export class FavouritemovielistComponent implements OnInit {

  constructor(private movieService:MovieserviceService,private router:Router,private toast :NgToastService) { }

  p:number=1;
  count: number = 10;
  value:number=5
  
  email:any;

  favouriteMovieList:any ;

  // private updateSubscription: Subscription;

  ngOnInit(): void {
    this.email=localStorage.getItem("email");
    console.log(this.email);    
    this.movieService.getAllFavouriteMoviesOfUser(this.email).subscribe(res=>{
      this.favouriteMovieList=res;
      console.log(this.favouriteMovieList[0]);

    })
  }

  deleteMovieFromFavouriteList(id:any){
    this.movieService.deleteFavouriteMovie(this.email,id).subscribe(res=>{
      console.log(res);
      location.reload() ;
      this.toast.success({detail:"DELETED",summary:"Movie Deleted Sucessfully !!",duration:100000})
    })
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
 

}
