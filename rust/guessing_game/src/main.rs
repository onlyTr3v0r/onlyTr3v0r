use rand::Rng;
use std::cmp::Ordering;

fn main() {
    let secret = rand::thread_rng().gen_range(1..=100);
    println!("debug: {}", secret);

    loop {
        let mut input = String::new();
        std::io::stdin()
            .read_line(&mut input)
            .expect("Failed to read line.. sadge ;(");
        let input: u32 = match input.trim().parse() {
            Ok(num) => num,
            Err(_) => continue,
        };

        println!("You guessed: {}", input);

        match input.cmp(&secret) {
            Ordering::Less => println!("Too low!"),
            Ordering::Greater => println!("Too high!"),
            Ordering::Equal => {
                println!("Correct!");
                break;
            }
        }
    }
}
