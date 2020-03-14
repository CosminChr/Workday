
export function parseDate(theDate: any): Date {
  const day = theDate.toString().substring(0, 2);
  const month = theDate.toString().substring(3, 5);
  const year = theDate.toString().substring(6, 10);
  return new Date(year, Number(month - 1), Number(day) + 1);
}

export function formatDate(theDate: any): String {
  const day = theDate.toString().substring(8, 10);
  const month = theDate.toString().substring(5, 7);
  const year = theDate.toString().substring(0, 4);
  return day + '-' + month + '-' + year;
}


export function dateDifference(date1: Date, date2: Date) :number {
  const diffTime = Math.abs(new Date(date2).getTime() - new Date(date1).getTime());
  const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
  return diffDays;
}
