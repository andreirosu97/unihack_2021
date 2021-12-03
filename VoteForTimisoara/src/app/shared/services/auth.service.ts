import { Observable, Subject } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { ErrorHandler } from '../helpers/error-handler';
import { State } from '../app.state';
import { config } from '../config';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  destroyed$: Subject<void> = new Subject<void>();

  isUserLoggedIn = false;

  constructor(
    private store: Store<State>,
    private router: Router,
    private http: HttpClient,
    private errorHandler: ErrorHandler
  ) {}

  register(id: number): Observable<any> {
    return this.http.post<any>(config.loginUrl, id).pipe(
      tap((data) => {
        console.log(data);
      }),
      catchError(this.errorHandler.handleError<any>('register'))
    );
  }
}
