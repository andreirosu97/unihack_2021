import { Observable, Subject } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { ErrorHandler } from '../helpers/error-handler';
import { State } from '../app.state';
import { config } from '../config';
import { catchError, tap } from 'rxjs/operators';
import * as fromShared from '../state/shared.selectors';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  isUserLoggedIn$ = this.store.select(fromShared.isUserLogged);

  isUserLoggedIn = false;

  constructor(
    private store: Store<State>,
    private router: Router,
    private http: HttpClient,
    private errorHandler: ErrorHandler
  ) {
    this.isUserLoggedIn$.subscribe((isUserLoggedIn) => {
      this.isUserLoggedIn = isUserLoggedIn;
    });
  }

  register(id: number): Observable<any> {
    return this.http.get<any>(config.loginUrl + id);
  }
}
