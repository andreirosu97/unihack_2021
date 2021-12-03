import { Component, OnInit } from '@angular/core';
import { AuthService } from './shared/services/auth.service';
import { catchError, tap } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'VoteForTimisoara';
  test = 0;

  constructor(public auth: AuthService) {}

  ngOnInit(): void {
    // this.auth
    //   .register(5)
    //   .pipe(
    //     catchError(() => of([])),
    //     tap((response) => {
    //       this.test = response;
    //     })
    //   )
    //   .subscribe();
  }
}
