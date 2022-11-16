import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FavouritemovielistComponent } from './Components/favouritemovielist/favouritemovielist.component';
import { LoginComponent } from './Components/login/login.component';
import { PopularmoviesComponent } from './Components/popularmovies/popularmovies.component';
import { RecommendedMoviesComponent } from './Components/recommended-movies/recommended-movies.component';
import { RegisterComponent } from './Components/register/register.component';
import { TopratedmoviesComponent } from './Components/topratedmovies/topratedmovies.component';
import { TrendingmoviesComponent } from './Components/trendingmovies/trendingmovies.component';
import { AuthenticationguardGuard } from './Guards/authenticationguard.guard';
import { DashboardComponent } from './Schematics/dashboard/dashboard.component';
import { HeaderComponent } from './Schematics/header/header.component';


const routes: Routes = [
  {
    path:"",
    component:DashboardComponent
  },
  {
    path:"favmovies",
    component:FavouritemovielistComponent,
    canActivate:[AuthenticationguardGuard]
  },
  {
    path:"header",
    component:HeaderComponent,
  },
  {
    path:"header/opendailog1",
    component:HeaderComponent
  },
  {
    path:"signup",
    component:RegisterComponent
  },
  {
    path:"signin",
    component:LoginComponent
  },
  {
    path:"trendingmovies",
    component:TrendingmoviesComponent,
  },
  {
        path:"topratedmovies",
        component:TopratedmoviesComponent
  },
  {
        path:"popularmovies",
        component:PopularmoviesComponent
  },
  {
    path:"signout",
    redirectTo:"/"
  },
  {
    path:"recommendmovies",
    component:RecommendedMoviesComponent
  }
 
];
  
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
