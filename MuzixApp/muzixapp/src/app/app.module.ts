import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { HeaderComponent } from './Schematics/header/header.component';
import { DashboardComponent } from './Schematics/dashboard/dashboard.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import {MatInputModule} from '@angular/material/input';
import { LoginComponent } from './Components/login/login.component';
import { RegisterComponent } from './Components/register/register.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgToastModule } from 'ng-angular-popup';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatTabsModule} from '@angular/material/tabs';
import { TrendingmoviesComponent } from './Components/trendingmovies/trendingmovies.component';
import { TopratedmoviesComponent } from './Components/topratedmovies/topratedmovies.component';
import { PopularmoviesComponent } from './Components/popularmovies/popularmovies.component';
import { RecommendedMoviesComponent } from './Components/recommended-movies/recommended-movies.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { FavouritemovielistComponent } from './Components/favouritemovielist/favouritemovielist.component';
import { ViewprofileComponent } from './Components/viewprofile/viewprofile.component';
import { EditprofileComponent } from './Components/editprofile/editprofile.component';

@NgModule({
  declarations: [   
    AppComponent,
    HeaderComponent,
    DashboardComponent,
    RegisterComponent,
    LoginComponent,
    TrendingmoviesComponent,
    TopratedmoviesComponent,
    PopularmoviesComponent,
    RecommendedMoviesComponent,
    FavouritemovielistComponent,
    ViewprofileComponent,
    EditprofileComponent,
  ],            
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    MatInputModule,
    MatDialogModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    FlexLayoutModule,
    NgToastModule,
    MatTooltipModule,
    MatTabsModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
