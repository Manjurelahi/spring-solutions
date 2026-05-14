import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './service/auth.service';
import { HeaderComponent } from "./header/header.component";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent],
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  title = 'JWT';

  constructor(public authService: AuthService) {
    this.authService.getCsrf();

   }
  ngOnInit(): void {
   this.authService.getCsrf();
  }
}
