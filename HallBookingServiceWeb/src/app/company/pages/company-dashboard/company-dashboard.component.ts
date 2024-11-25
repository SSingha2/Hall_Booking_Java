import { Component } from '@angular/core';
import { CompanyService } from '../../services/company.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-company-dashboard',
  templateUrl: './company-dashboard.component.html',
  styleUrl: './company-dashboard.component.scss'
})
export class CompanyDashboardComponent {

  constructor(private companyService: CompanyService,
    private notification: NzNotificationService
  ){}

  bookings:any;

  ngOnInit(){
    this.getAllHallBookings();
  }

  getAllHallBookings(){
    this.companyService.getAllHallBookins().subscribe(res =>{
      console.log(res)
      this.bookings = res;
    })
  }

  changeBookingStatus(bookingId: number, status: string){
    this.companyService.changeBookingStatus(bookingId, status).subscribe(res =>{
      this.notification
      .success(
        'SUCCESS',
        `Booking status changed successfully`,
        { nzDuration: 5000 }
      );
      this.getAllHallBookings();
    }, error => {
      this.notification
      .error(
        'ERROR',
        `${error.message}`,
        { nzDuration: 5000 }
      );
    })
  }
}