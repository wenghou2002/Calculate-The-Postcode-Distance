import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DistanceCalculatorComponent } from './distance-calculator/distance-calculator.component';
import { PostcodeUpdaterComponent } from './postcode-updater/postcode-updater.component';
import { PostcodeListComponent } from './postcode-list/postcode-list.component';

const routes: Routes = [
  { path: 'distance-calculator', component: DistanceCalculatorComponent },
  { path: 'postcode-updater', component: PostcodeUpdaterComponent },
  { path: 'postcode-list', component: PostcodeListComponent },
  { path: '', redirectTo: '/distance-calculator', pathMatch: 'full' },
  { path: '**', redirectTo: '/distance-calculator' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { } 