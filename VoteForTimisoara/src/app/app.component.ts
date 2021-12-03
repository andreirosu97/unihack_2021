import { Component, OnInit } from '@angular/core';
import { AuthService } from './shared/services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'VoteForTimisoara';

  constructor(public auth: AuthService) {}

  ngOnInit(): void {
    this.auth.register(5);
  }
}
