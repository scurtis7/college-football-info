import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { DataService } from "../../../service/data.service";
import { Team } from "../../../model/team";
import { MatTableDataSource } from "@angular/material/table";
import { MatSort } from "@angular/material/sort";

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.scss']
})
export class TeamsComponent implements OnInit {

  @ViewChild(MatSort) sort: MatSort;

  teams: Team[];
  displayedColumns: string[] = ['id', 'school', 'abbreviation', 'classification', 'conference', 'division'];
  dataSource: MatTableDataSource<Team> = new MatTableDataSource<Team>();
  isLoading: boolean = false;

  constructor(private dataService: DataService) {
  }

  ngOnInit() {
    this.isLoading = true;
    this.dataService.getFbsTeams()
      .subscribe(
        (data: Team[]) => {
          this.teams = data;
          this.resetDatasource();
          this.isLoading = false;
        }
      );
  }

  resetDatasource() {
    this.dataSource.data = this.teams;
    this.dataSource.sort = this.sort;
  }

}
