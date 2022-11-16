import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Result } from '../classes/result';

@Injectable({
  providedIn: 'root'
})
export class MovieserviceService {

  constructor(private httpclient:HttpClient) { }
 
showtrendingMovies(pageNo:any)
{
  return this.httpclient.get<Result>(`https://api.themoviedb.org/3/trending/all/day?api_key=8426dc02f708dc9702fe670209c75b98&language=en-US&page=${pageNo}`);
}
    
showPopularMovies(pageNo:any)
{
  return this.httpclient.get<Result>(`https://api.themoviedb.org/3/movie/popular?api_key=8426dc02f708dc9702fe670209c75b98&language=en-US&page=${pageNo}`);
}

showTopRatedMovies(pageNo:any){
  return this.httpclient.get<Result>(`https://api.themoviedb.org/3/movie/top_rated?api_key=8426dc02f708dc9702fe670209c75b98&language=en-US&page=${pageNo}`);
}

showRecommendedMovies(pageNo:any){
  return this.httpclient.get<Result>(`https://api.themoviedb.org/3/movie/429473/recommendations?api_key=8426dc02f708dc9702fe670209c75b98&language=en-US&page=${pageNo}`)
}

searchMovieBasedOnName(name:any){
  return this.httpclient.get<Result>(`https://api.themoviedb.org/3/search/movie?api_key=8426dc02f708dc9702fe670209c75b98&query=${name}`)  ;
}

addMovieToFavouritelist(email:any,movie:any):Observable<any>{
  console.log(email);
  
  return this.httpclient.post(`http://localhost:9000/usermovie/favouritelist/${email}`,movie)

}

getAllFavouriteMoviesOfUser(email:any){
  return this.httpclient.get(`http://localhost:9000/usermovie/favouritemovielist/${email}`)
}

deleteFavouriteMovie(email:any,id:any){
  return this.httpclient.delete(`http://localhost:9000/usermovie/delete/${email}/${id}`)
}

getUser(email:any):Observable<any>{
  return this.httpclient.get(`http://localhost:9000/usermovie/get/user/${email}`)
}

  emailOfLoggedInUser:any;
  storeEmailFromLogInComp(data:any){
    this.emailOfLoggedInUser=data ;
  }
    
  updateUser(email:string,data:any){
    return this.httpclient.put(`http://localhost:9000/usermovie/update/${email}`,data)
  }
     
}
