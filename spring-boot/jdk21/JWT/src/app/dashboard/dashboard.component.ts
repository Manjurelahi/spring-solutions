import { Component } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent{
isAdmin: boolean;
isUser: boolean;
 constructor(private authService: AuthService) {
  this.isAdmin = this.authService.isAdmin();
  this.isUser = this.authService.isUser();
 }
}
