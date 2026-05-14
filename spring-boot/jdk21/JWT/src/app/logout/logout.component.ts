import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-logout',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './logout.component.html'
})
export class LogoutComponent {
  private router = inject(Router);

  goToLogin() {
    this.router.navigate(['/']);
  }
}
