import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Team } from "../model/team";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  getTeams(): Observable<Team[]> {
    return this.http.get<Team[]>('teams', {
      headers: this.getHeaders()
    });
  }


  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Accept': 'application/json'
    });
  }

}
