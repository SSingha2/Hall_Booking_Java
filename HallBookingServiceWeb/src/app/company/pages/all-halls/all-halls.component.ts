import { Component } from '@angular/core';
import { CompanyService } from '../../services/company.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-all-halls',
  templateUrl: './all-halls.component.html',
  styleUrl: './all-halls.component.scss'
})
export class AllHallsComponent {

  halls:any;

  constructor(private companyService: CompanyService,
    private notification: NzNotificationService
  ){}

  ngOnInit(){
    this.getAllHallsByUserId();
  }

  getAllHallsByUserId(){
    this.companyService.getAllHallsByUserId().subscribe(res =>{
    //  console.log(res);
      this.halls = res;
    })
  }

  updateImg(img){
    return `data:image/jpeg;base64,${img}`;
  }

  deleteHall(hallId:any){
    this.companyService.deleteHall(hallId).subscribe(res => {
      this.notification
      .success(
        'SUCCESS',
        `Hall deleted successfully`,
        { nzDuration: 5000 }
    );
    this.getAllHallsByUserId();
    })
  }
}
