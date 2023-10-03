import { Component, OnInit } from '@angular/core';
import { DataService } from "../../../service/data.service";
import { Team } from "../../../model/team";

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.scss']
})
export class TeamsComponent implements OnInit {

  teams: Team[];

  constructor(private dataService: DataService) {
  }

  ngOnInit() {
    this.dataService.getTeams()
      .subscribe(
        (data: Team[]) => this.teams = data
      );
  }

}
