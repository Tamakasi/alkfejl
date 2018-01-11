import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { User } from '../../classes/user';

@Component({
  selector: 'app-login-view',
  templateUrl: './login-view.component.html',
  styleUrls: ['./login-view.component.css'],
  providers: [AuthService]
})
export class LoginViewComponent implements OnInit {
  private loginError: string = '';
  private regClicked: boolean = false;
  private regError: string = '';
  private emailInput = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    if (this.route.snapshot.queryParamMap.get('from')) {
      this.loginError = 'Az adott oldal eléréséhez bejelentkezés szükséges!';
    }
  }

  private tryLogin(name: string, password: string) {
    this.authService.login(name, password).subscribe((success: boolean) => {
      if (success) {
        const redirectTo: string = this.route.snapshot.queryParamMap.get('from') || 'folder';
        this.router.navigate([redirectTo]);
      } else {
        this.loginError = 'Hibás felhasnzálónév vagy jelszó';
      }
    });
  }

  private tryRegister(name: string, password: string, email: string) {
    console.log(name, password, email);
    this.authService.register(name, password, email).subscribe((success: boolean) => {
      if (success) {
        console.log("reg success");
        const redirectTo: string = this.route.snapshot.queryParamMap.get('from') || 'folder';
      } else {
        this.regError = 'Üresen hagyott mezők vagy foglalt felhasználó név';
        console.log("reg error");
      }
    });
  
  }

}
