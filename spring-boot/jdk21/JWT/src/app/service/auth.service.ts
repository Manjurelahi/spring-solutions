import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  xsrfToken: string = "";

  constructor(private http: HttpClient) {}

  isLoggedInSignal = signal<boolean>(this.isLoggedIn());

  login(credentials: any) {
    return this.http.post("http://localhost:8080/api/v1/login", credentials, 
    {headers: new HttpHeaders({"X-XSRF-TOKEN": this.xsrfToken}), withCredentials: true});
  }

  getCsrf() {
    return this.http.get("http://localhost:8080/csrf/token", {withCredentials: true}
    ).subscribe((data: any) => this.xsrfToken = data.token);
  }

  private isLoggedIn():boolean {    
    if (localStorage.getItem('isLoggedIn') === 'true') {
      return true;
    }
    return false;
  } 

  isAdmin():boolean {
    if ('ROLE_ADMIN'===localStorage.getItem('role')) {
      return true;
    }
    return false;
  }

  isUser():boolean {
    if ('ROLE_USER'===localStorage.getItem('role')) {
      return true;
    }
    return false;
  }

  logout(){
    localStorage.removeItem('username');
    localStorage.removeItem('role');
    localStorage.removeItem('isLoggedIn');
    this.isLoggedInSignal.set(false);
    return this.http.post("http://localhost:8080/api/v1/logout", {}, 
    {headers: new HttpHeaders({"X-XSRF-TOKEN": this.xsrfToken}), withCredentials: true});    
  }
}
