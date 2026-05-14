import { Component, OnInit, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  authService = inject(AuthService);
  private router = inject(Router);
  isLoggedIn = false; 

  constructor() {
    this.isLoggedIn = this.authService.isLoggedInSignal();
  }
  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedInSignal();
  }
  

  public onLogout() {    
    this.authService.logout().subscribe({
      next: (res) => {
        console.log('Logged out!', res);
        this.router.navigateByUrl('logout');
      },
      error: (err) => console.error('logout failed', err)
    });
  }

}
