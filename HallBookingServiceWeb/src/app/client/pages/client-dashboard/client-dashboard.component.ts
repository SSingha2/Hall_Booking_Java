import { Component } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-client-dashboard',
  templateUrl: './client-dashboard.component.html',
  styleUrl: './client-dashboard.component.scss'
})
export class ClientDashboardComponent {

  halls: any = [];
  validateForm: FormGroup;

  constructor(private clientService: ClientService,
    private  fb: FormBuilder
  ){}

  getAllHalls(){
    this.clientService.getAllHalls().subscribe(res => {
      this.halls = res;
    })
  }

  ngOnInit(){
    this.validateForm = this.fb.group({
      name: [null, [Validators.required]]
    })
    this.getAllHalls();
  }

  updateImg(img){
    return `data:image/jpeg;base64,${img}`;
  }

  searchHallByName(){
    this.clientService.searchHallByName(this.validateForm.get(['name']).value).subscribe(res => {
      this.halls = res;
    });
  }

}
