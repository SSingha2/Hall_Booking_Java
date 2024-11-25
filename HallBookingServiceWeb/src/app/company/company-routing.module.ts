import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyComponent } from './company.component';
import { CompanyDashboardComponent } from './pages/company-dashboard/company-dashboard.component';
import { CreateHallComponent } from './pages/create-hall/create-hall.component';
import { AllHallsComponent } from './pages/all-halls/all-halls.component';
import { UpdateHallComponent } from './pages/update-hall/update-hall.component';

const routes: Routes = [
  { path: '', component: CompanyComponent },
  { path: 'dashboard', component: CompanyDashboardComponent },
  { path: 'hall', component: CreateHallComponent },
  { path: 'halls', component: AllHallsComponent },
  { path: 'update/:id', component: UpdateHallComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompanyRoutingModule { }
