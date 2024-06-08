//
// async function fetchData() {
//     const token = localStorage.getItem('jwt');
//     if (!token) {
//         console.error('No JWT found, please login first');
//         return;
//     }
//
//     try {
//         const response = await fetch('/api/protected', {
//             method: 'GET',
//             headers: {
//                 'Authorization': 'Bearer ' + token
//             }
//         });
//
//         if (!response.ok) {
//             throw new Error('Failed to fetch data');
//         }
//
//         const data = await response.json();
//         console.log(data);
//     } catch (error) {
//         console.error(error.message);
//     }
// }
