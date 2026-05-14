import { Component, OnInit, inject } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl, Validators, FormsModule, NgForm } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Router, RouterOutlet } from '@angular/router';
import { HeaderComponent } from "../header/header.component";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  private authService = inject(AuthService);
  private router = inject(Router);
  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
  });
  public isLoggedIn = false;
  private response: any | undefined;
  public role: string | undefined;

  constructor() {
    if (this.isLoggedIn) {
      this.loginSuccess();
    }
  }


  ngOnInit(): void {
    if (this.isLoggedIn) {
      this.loginSuccess();
    }
  }


  loginSuccess() {
    this.isLoggedIn = true;
    this.role = this.response.role;
    localStorage.setItem('username', this.response.username);
    localStorage.setItem('role', this.response.role);
    localStorage.setItem('isLoggedIn', JSON.stringify(this.isLoggedIn));
    this.authService.isLoggedInSignal.set(true);
    this.router.navigateByUrl('dashboard');
  }


  onLogin() {
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value).subscribe({
        next: (res) => {
          console.log('Logged in!', res);
          this.response = res;
          this.loginSuccess();
        },
        error: (err) => console.error('Login failed', err)
      });
    } else {
      throw new Error('Login Form is not valid');
    }
  }

}


