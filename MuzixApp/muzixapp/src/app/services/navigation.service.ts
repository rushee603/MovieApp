import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavigationService {
isLogIn=false;
redirect:string | null=null;
  constructor() { }
}
