import { Component, OnInit, ViewChild } from '@angular/core';
import { BreakpointObserver, BreakpointState } from "@angular/cdk/layout";
import { Router } from "@angular/router";
import { MatSidenav } from "@angular/material/sidenav";

const SMALL_WIDTH_BREAKPOINT = 720;

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  public isScreenSmall: boolean | undefined;

  constructor(private breakpointObserver: BreakpointObserver, private router: Router) { }

  @ViewChild(MatSidenav) sidenav: MatSidenav | undefined;

  ngOnInit(): void {
    this.breakpointObserver
      .observe([ `(max-width: ${SMALL_WIDTH_BREAKPOINT}px)`])
      .subscribe((state: BreakpointState) => {
        this.isScreenSmall = state.matches;
      });

    this.router.events.subscribe(() => {
      if (this.isScreenSmall) {
        this.sidenav?.close();
      }
    });
  }

}
