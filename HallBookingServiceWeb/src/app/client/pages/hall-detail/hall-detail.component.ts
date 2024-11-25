import { Component } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { UserStorageService } from '../../../basic/services/storage/user-storage.service';

@Component({
  selector: 'app-hall-detail',
  templateUrl: './hall-detail.component.html',
  styleUrl: './hall-detail.component.scss'
})
export class HallDetailComponent {

  hallId = this.activatedRoute.snapshot.params['hallId'];
  avatarUrl: any;
  hall: any;
  reviews: any;

  validateForm!: FormGroup;

  constructor(private clientService: ClientService,
    private activatedRoute: ActivatedRoute,
    private notification: NzNotificationService,
    private router: Router,
    private fb: FormBuilder
  ){}

  ngOnInit(){
    this.validateForm = this.fb.group({
      bookDate: [null, [Validators.required]]
    })
    this.getHallDetailsByHallId();
  }

  getHallDetailsByHallId(){
    this.clientService.getHallDetailsById(this.hallId).subscribe(res=>{
      console.log(res);
      this.avatarUrl = 'data:image/jpeg;base64,'+ res.hallDto.returnedImg;
      this.hall = res.hallDto;
      this.reviews = res.reviewDTOList;
    })
  }

  bookHall(){
    const bookServiceDTO = {
      bookDate: this.validateForm.get(['bookDate']).value,
      hallId: this.hallId,
      userId: UserStorageService.getUserId()
    }

    this.clientService.bookHall(bookServiceDTO).subscribe(res => {
      this.notification
      .success(
        'SUCCESS',
        `Request posted successfully`,
        { nzDuration : 5000 }
      );
      this.router.navigateByUrl('/client/bookings');
    })
  }

}
